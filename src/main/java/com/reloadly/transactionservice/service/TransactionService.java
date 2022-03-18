package com.reloadly.transactionservice.service;

import com.reloadly.transactionservice.dto.request.DepositRequestDto;
import com.reloadly.transactionservice.dto.request.TransferRequestDto;
import com.reloadly.transactionservice.dto.request.WithdrawalRequestDto;
import com.reloadly.transactionservice.dto.response.*;

public interface TransactionService {
    AccountTransactionResponseDto depositFunds(DepositRequestDto depositRequestDto, String bearerToken);
    TransferResponseDto transferFunds(TransferRequestDto transferRequestDto, String bearerToken);
    WithdrawResponseDto withdrawFunds(WithdrawalRequestDto withdrawalRequestDto);
    TransactionResponseDto getAccountTransactions(long accountNumber);

}
