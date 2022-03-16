package com.reloadly.transactionservice.service;

import com.reloadly.transactionservice.dto.request.TransferRequestDto;
import com.reloadly.transactionservice.dto.response.TransactionNoticeResponse;

public interface MailSenderService {
    TransactionNoticeResponse sendTransactionNotice(TransferRequestDto transferRequestDto);
}
