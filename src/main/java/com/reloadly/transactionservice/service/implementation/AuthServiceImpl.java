package com.reloadly.transactionservice.service.implementation;

import com.reloadly.transactionservice.dto.request.LoginRequestDto;
import com.reloadly.transactionservice.dto.response.LoginResponseDto;
import com.reloadly.transactionservice.exceptions.CustomException;
import com.reloadly.transactionservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RestTemplate restTemplate;
    @Value("${auth.user.base.url}")
    private String AUTH_BASE_URL;

    @Override
    public LoginResponseDto authenticateUser(LoginRequestDto loginRequestDto) {
        try {
            restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
            final MultiValueMap<String, String> formVars = new LinkedMultiValueMap<>();
            formVars.add("email", loginRequestDto.getEmail());
            formVars.add("password", loginRequestDto.getPassword());

            return  restTemplate.postForObject(AUTH_BASE_URL, formVars, LoginResponseDto.class);
        } catch (Exception exp) {
            log.error("exception occurred during authentication :: message [{}]", exp.getMessage());
            throw new CustomException("exception occurred during authentication");
        }
    }


}
