package com.reloadly.transactionservice.service;

import com.reloadly.transactionservice.dto.request.DepositRequestDto;
import com.reloadly.transactionservice.dto.request.TransferRequestDto;
import com.reloadly.transactionservice.dto.request.WithdrawalRequestDto;
import com.reloadly.transactionservice.dto.response.*;
import com.reloadly.transactionservice.entity.Transaction;

public interface TransactionService {
    DepositResponse depositFunds(DepositRequestDto depositRequestDto, String bearerToken);
    TransferResponseDto transferFunds(TransferRequestDto transferRequestDto, String bearerToken);
    WithdrawResponseDto withdrawFunds(WithdrawalRequestDto withdrawalRequestDto, String bearerToken);
    PaginatedResponse<Transaction> getTransactions(int start, int limit);

}
