package com.reloadly.transactionservice.dto.request;

import com.reloadly.transactionservice.constants.TransactionStatus;
import com.reloadly.transactionservice.constants.TransactionType;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

public class DepositRequestDto {

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "transaction_type")
    private final TransactionType transactionType = TransactionType.DEPOSIT;

    @Column(name = "sender_account_number")
    private long senderAccountNumber;

    @Column(name = "receiver_account_number")
    private String receiverAccountNumber;

}
