package com.reloadly.transactionservice.dto.request;

import com.reloadly.transactionservice.constants.TransactionType;

import javax.persistence.Column;
import java.math.BigDecimal;

public class TransferRequestDto {
    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "transaction_type")
    private final TransactionType transactionType = TransactionType.TRANSFER;

    @Column(name = "sender_account_number")
    private long senderAccountNumber;

    @Column(name = "receiver_account_number")
    private String receiverAccountNumber;
}
