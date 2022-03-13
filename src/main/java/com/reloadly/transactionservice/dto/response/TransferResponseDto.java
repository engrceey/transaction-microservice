package com.reloadly.transactionservice.dto.response;

import java.math.BigDecimal;

public class TransferResponseDto {

    private BigDecimal amount;

    private long senderAccountNumber;

    private String receiverAccountNumber;
}
