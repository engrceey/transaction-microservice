package com.reloadly.transactionservice.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountTransactionResponseDto<T> {
    private String statusMessage;
    private boolean isSuccessful;
    private T data;
}
