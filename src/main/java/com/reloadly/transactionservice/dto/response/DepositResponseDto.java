package com.reloadly.transactionservice.dto.response;

import lombok.Data;

@Data
public class DepositResponseDto {

    private String statusCode;
    private String receiverName;
    private String depositAccountNumber;
    private boolean transactionSuccessful;

}
