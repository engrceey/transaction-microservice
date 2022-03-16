package com.reloadly.transactionservice.dto.request;

import com.reloadly.transactionservice.constants.TransactionType;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;


@Data
public class WithdrawalRequestDto {
    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "transaction_type")
    private final TransactionType transactionType = TransactionType.WITHDRAW;

    @Column(name = "sender_account_number")
    private long senderAccountNumber;

  }