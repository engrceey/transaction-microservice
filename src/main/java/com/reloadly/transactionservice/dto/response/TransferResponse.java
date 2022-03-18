package com.reloadly.transactionservice.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferResponse {
    private String statusMessage;
    private boolean isSuccessful;
    private TransferResponseDto data;

}
