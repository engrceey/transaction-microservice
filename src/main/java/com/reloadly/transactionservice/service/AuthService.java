package com.reloadly.transactionservice.service;

import com.reloadly.transactionservice.dto.request.LoginRequestDto;
import com.reloadly.transactionservice.dto.response.LoginResponseDto;

public interface AuthService {

    LoginResponseDto authenticateUser(LoginRequestDto loginRequestDto);
}
