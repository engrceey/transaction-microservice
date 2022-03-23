package com.reloadly.transactionservice.dto.request;

import lombok.Data;

@Data
public class EmailRequestDto {

    private String subject;

    private String recipient;

    private String body;

    private String sender;

    private String attachment;

    private String cc;

    private String bcc;

}
