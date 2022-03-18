package com.reloadly.transactionservice.dto.response;

import lombok.Data;

@Data
public class TransferResponseDto {
    private String statusCode;
    private String senderName;
    private long sendAccountNumber;
    private String receiverName;
    private boolean isTransactionSuccessful;
}


