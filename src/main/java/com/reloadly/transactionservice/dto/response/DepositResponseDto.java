package com.reloadly.transactionservice.dto.response;

import javax.persistence.Column;
import java.math.BigDecimal;

public class DepositResponseDto {
    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "sender_account_number")
    private long senderAccountNumber;

    @Column(name = "receiver_account_number")
    private String receiverAccountNumber;
}
