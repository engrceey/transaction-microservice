package com.reloadly.transactionservice.dto.response;

import lombok.Data;

@Data
public class DepositResponse {
    private String statusMessage;
    private boolean isSuccessful;
    private DepositResponseDto data;
}