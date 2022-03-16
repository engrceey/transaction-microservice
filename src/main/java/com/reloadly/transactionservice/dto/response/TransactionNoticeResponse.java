package com.reloadly.transactionservice.dto.response;

import lombok.Data;

@Data
public class TransactionNoticeResponse {

    private String statusMessage;
    private boolean isSuccessful;
    private boolean data;
}
