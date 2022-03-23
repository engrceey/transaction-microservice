package com.reloadly.transactionservice.service.implementation;

import com.reloadly.transactionservice.constants.TransactionStatus;
import com.reloadly.transactionservice.constants.TransactionType;
import com.reloadly.transactionservice.dto.request.DepositRequestDto;
import com.reloadly.transactionservice.dto.request.TransferRequestDto;
import com.reloadly.transactionservice.dto.request.WithdrawalRequestDto;
import com.reloadly.transactionservice.dto.response.*;
import com.reloadly.transactionservice.entity.Transaction;
import com.reloadly.transactionservice.exceptions.CustomException;
import com.reloadly.transactionservice.exceptions.ResourceNotFoundException;
import com.reloadly.transactionservice.repository.TransactionRepository;
import com.reloadly.transactionservice.service.MailSenderService;
import com.reloadly.transactionservice.service.TransactionService;
import com.reloadly.transactionservice.utils.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.transaction.annotation.Transactional;
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
    private final MailSenderService mailSenderService;

    @Override
    public DepositResponse depositFunds(DepositRequestDto depositRequestDto, String bearerToken) {
        log.info("Service depositFunds - deposit funds for ::[{}]", depositRequestDto.getReceiver());
        final String url = String.format("%s/deposit-fund", ACCOUNT_TRANSACTION_BASE_URL);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, bearerToken);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DepositRequestDto> entity = new HttpEntity<>(depositRequestDto, httpHeaders);

        log.info("Initiating request to deposit fund: [{}] :::", depositRequestDto.getReceiver());

        ResponseEntity<DepositResponse> response = null;
        try {
            response  = restTemplate.exchange(url,HttpMethod.POST, entity, DepositResponse.class);
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

            mailSenderService.sendDepositTransactionNotice(ModelMapperUtils.map(depositTransaction, new TransactionNotificationEmailDto()));

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

            mailSenderService.sendTransferTransactionNotice(ModelMapperUtils.map(transaction, new TransactionNotificationEmailDto()));

            return response.getBody().getData();
        }
        return Objects.requireNonNull(response.getBody()).getData();
    }

    @Override
    public WithdrawResponseDto withdrawFunds(WithdrawalRequestDto withdrawalRequestDto, String bearerToken) {
        log.info("Service withdraw - withdraw funds for ::[{}]", withdrawalRequestDto.getAmount());
        final String url = String.format("%s/withdraw-fund", ACCOUNT_TRANSACTION_BASE_URL);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, bearerToken);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<WithdrawalRequestDto> entity = new HttpEntity<>(withdrawalRequestDto, httpHeaders);

        log.info("Initiating request to with fund: [{}] :::", withdrawalRequestDto.getAmount());

        ResponseEntity<WithdrawResponse > response = null;
        try {
            response  = restTemplate.exchange(url,HttpMethod.POST, entity, WithdrawResponse.class);
        } catch (Exception exp) {
            log.error("An error occurred calling external api for :: [{}] :: Error msg :: [{}]",
                    withdrawalRequestDto.getAmount() , exp.getMessage());
            throw new CustomException("An error occurred calling external api");
        }

        String transactionId = UUID.randomUUID().toString();

        if (response.getStatusCode().is2xxSuccessful()) {
            Transaction transaction = new Transaction();
            transaction.setTransactionType(TransactionType.WITHDRAW);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            transaction.setAmount(withdrawalRequestDto.getAmount());
            transaction.setReceiver(Objects.requireNonNull(response.getBody()).getData().getName());
            transaction.setSender(response.getBody().getData().getName());
            transaction.setReceiverAccountNumber(response.getBody().getData().getAccountNum());
            transaction.setSenderAccountNumber(response.getBody().getData().getAccountNum());
            transaction.setTransactionId(transactionId);
            transactionRepository.save(transaction);

            mailSenderService.sendWithdrawalTransactionNotice(ModelMapperUtils.map(transaction, new TransactionNotificationEmailDto()));

            return response.getBody().getData();
        }
        return Objects.requireNonNull(response.getBody()).getData();
    }

    @Override
    @Transactional(readOnly = true)
    public PaginatedResponse<Transaction> getTransactions(int start, int limit) {
        Page<Transaction> transactions = transactionRepository.findAll(PageRequest.of(start, limit));
        if (transactions.isEmpty()) {
            throw new ResourceNotFoundException("No Transaction found");
        }
        return PaginatedResponse.<Transaction>builder()
                .content(ModelMapperUtils.mapAll(transactions.getContent(), Transaction.class))
                .totalElements(transactions.getTotalElements())
                .build();
    }

}
