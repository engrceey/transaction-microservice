package com.reloadly.transactionservice.dto.response;

import lombok.Data;

@Data
public class WithdrawResponseDto {
    private long accountNum;
    private String name;
    private String status;
    private boolean isSuccessful;
}
