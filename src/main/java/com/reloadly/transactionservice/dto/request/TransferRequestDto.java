package com.reloadly.transactionservice.dto.request;

import com.reloadly.transactionservice.constants.TransactionType;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class TransferRequestDto {
    @NotNull
    private Long receiverAccountNumber;

    @NotNull(message = "account cannot be empty")
    @Min(value = 0)
    private BigDecimal amount;

    @NotBlank(message = "sender cannot be blank")
    private String sender;

    @NotBlank(message = "receiver cannot be blank")
    private String receiver;
}
