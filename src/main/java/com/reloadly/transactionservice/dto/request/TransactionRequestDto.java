package com.reloadly.transactionservice.dto.request;

import lombok.Data;

public class TransactionRequestDto {
    @Data
    public static class TransactionNoticeRequestDto {
        private String sender;
        private String receiver;
        private String message;
    }
}
