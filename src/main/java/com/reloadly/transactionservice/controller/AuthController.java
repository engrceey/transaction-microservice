package com.reloadly.transactionservice.controller;


import com.reloadly.transactionservice.dto.request.LoginRequestDto;
import com.reloadly.transactionservice.dto.response.ApiResponse;
import com.reloadly.transactionservice.dto.response.LoginResponseDto;
import com.reloadly.transactionservice.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> authenticateUser(@RequestBody @Valid final LoginRequestDto loginRequestDto){
        log.info("Controller authenticateUser - user email :: [{}]",loginRequestDto.getEmail());
        LoginResponseDto response = authService.authenticateUser(loginRequestDto);

        return ResponseEntity.ok(ApiResponse.<LoginResponseDto>builder()
                .isSuccessful(true)
                .statusMessage("authenticated")
                .data(response)
                .build()
        );
    }


}
