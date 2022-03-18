package com.reloadly.transactionservice.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
public class WithdrawalRequestDto {
    @NotNull(message = "must not be null")
    private BigDecimal amount;
  }
