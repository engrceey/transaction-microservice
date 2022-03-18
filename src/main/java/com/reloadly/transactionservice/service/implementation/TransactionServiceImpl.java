package com.reloadly.transactionservice.service.implementation;

import com.reloadly.transactionservice.constants.TransactionStatus;
import com.reloadly.transactionservice.constants.TransactionType;
import com.reloadly.transactionservice.dto.request.DepositRequestDto;
import com.reloadly.transactionservice.dto.request.TransferRequestDto;
import com.reloadly.transactionservice.dto.request.WithdrawalRequestDto;
import com.reloadly.transactionservice.dto.response.*;
import com.reloadly.transactionservice.entity.Transaction;
import com.reloadly.transactionservice.exceptions.CustomException;
import com.reloadly.transactionservice.repository.TransactionRepository;
import com.reloadly.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class TransactionServiceImpl implements TransactionService {

    @Value("${account.transaction.base.url}")
    private String ACCOUNT_TRANSACTION_BASE_URL;


    private final TransactionRepository transactionRepository;
    private final RestTemplate restTemplate;

    @Override
    public AccountTransactionResponseDto depositFunds(DepositRequestDto depositRequestDto, String bearerToken) {
        log.info("Service depositFunds - deposit funds for ::[{}]", depositRequestDto.getReceiver());
        final String url = String.format("%s/deposit-fund", ACCOUNT_TRANSACTION_BASE_URL);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, bearerToken);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DepositRequestDto> entity = new HttpEntity<>(depositRequestDto, httpHeaders);

        log.info("Initiating request to deposit fund: [{}] :::", depositRequestDto.getReceiver());

        ResponseEntity<AccountTransactionResponseDto> response = null;
        try {
            response  = restTemplate.exchange(url,HttpMethod.POST, entity,AccountTransactionResponseDto.class);
        } catch (Exception exp) {
            log.error("An error occurred calling external api to deposit fund for :: [{}] :: Error msg :: [{}]",
                    depositRequestDto.getReceiver(), exp.getMessage());
            throw new CustomException("An error occurred calling external api to deposit ");
        }

        String transactionId = UUID.randomUUID().toString();

        if (response.getStatusCode().is2xxSuccessful()) {
            Transaction depositTransaction = new Transaction();
            depositTransaction.setTransactionType(TransactionType.DEPOSIT);
            depositTransaction.setTransactionStatus(TransactionStatus.SUCCESS);
            depositTransaction.setAmount(depositRequestDto.getAmount());
            depositTransaction.setReceiver(depositRequestDto.getReceiver());
            depositTransaction.setSender(depositRequestDto.getSender());
            depositTransaction.setReceiverAccountNumber(depositRequestDto.getReceiverAccountNumber());
            depositTransaction.setTransactionId(transactionId);
            transactionRepository.save(depositTransaction);

            return response.getBody();
        }
        return response.getBody();
    }


    @Override
    public TransferResponseDto transferFunds(TransferRequestDto transferRequestDto, String bearerToken) {
        log.info("Service transferFunds - transfer funds for ::[{}]", transferRequestDto.getReceiverAccountNumber());
        final String url = String.format("%s/transfer-fund", ACCOUNT_TRANSACTION_BASE_URL);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, bearerToken);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TransferRequestDto> entity = new HttpEntity<>(transferRequestDto, httpHeaders);

        log.info("Initiating request to deposit fund: [{}] :::", transferRequestDto.getReceiverAccountNumber());

        ResponseEntity<TransferResponse > response = null;
        try {
            response  = restTemplate.exchange(url,HttpMethod.POST, entity, TransferResponse.class);
        } catch (Exception exp) {
            log.error("An error occurred calling external api for :: [{}] :: Error msg :: [{}]",
                    transferRequestDto.getReceiverAccountNumber(), exp.getMessage());
            throw new CustomException("An error occurred calling external api");
        }

        String transactionId = UUID.randomUUID().toString();

        if (response.getStatusCode().is2xxSuccessful()) {
            Transaction transaction = new Transaction();
            transaction.setTransactionType(TransactionType.TRANSFER);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            transaction.setAmount(transferRequestDto.getAmount());
            transaction.setReceiver(Objects.requireNonNull(response.getBody()).getData().getReceiverName());
            transaction.setSender(response.getBody().getData().getSenderName());
            transaction.setReceiverAccountNumber(transferRequestDto.getReceiverAccountNumber());
            transaction.setSenderAccountNumber(response.getBody().getData().getSendAccountNumber());
            transaction.setTransactionId(transactionId);
            transactionRepository.save(transaction);

            return response.getBody().getData();
        }
        return Objects.requireNonNull(response.getBody()).getData();
    }

    @Override
    public WithdrawResponseDto withdrawFunds(WithdrawalRequestDto withdrawalRequestDto) {
        return null;
    }

    @Override
    public TransactionResponseDto getAccountTransactions(long accountNumber) {
        return null;
    }

}
