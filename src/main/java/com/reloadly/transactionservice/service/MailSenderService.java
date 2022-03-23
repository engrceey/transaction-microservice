package com.reloadly.transactionservice.service;

import com.reloadly.transactionservice.dto.response.TransactionNotificationEmailDto;

public interface MailSenderService {
    void sendDepositTransactionNotice(TransactionNotificationEmailDto transactionNotificationEmailDto);
    void sendTransferTransactionNotice(TransactionNotificationEmailDto transactionNotificationEmailDto);
    void sendWithdrawalTransactionNotice(TransactionNotificationEmailDto transactionNotificationEmailDto);

}
