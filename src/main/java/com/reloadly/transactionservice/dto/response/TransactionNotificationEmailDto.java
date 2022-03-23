package com.reloadly.transactionservice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionNotificationEmailDto {
    private String sender;
    private String receiver;
    private String action;
    private String receiverEmail = "test-email@gmail.com";
    private BigDecimal amount;

}
