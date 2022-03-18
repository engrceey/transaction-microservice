package com.reloadly.transactionservice.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawResponse {
    private String statusMessage;
    private boolean isSuccessful;
    private WithdrawResponseDto data;
}
