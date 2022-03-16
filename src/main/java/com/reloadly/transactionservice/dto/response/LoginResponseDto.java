package com.reloadly.transactionservice.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class LoginResponseDto {

    @JsonAlias(value = "access_token")
    private String accessToken;
}
