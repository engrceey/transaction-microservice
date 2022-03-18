package com.reloadly.transactionservice.controller;


import com.reloadly.transactionservice.dto.request.DepositRequestDto;
import com.reloadly.transactionservice.dto.request.TransferRequestDto;
import com.reloadly.transactionservice.dto.request.WithdrawalRequestDto;
import com.reloadly.transactionservice.dto.response.*;
import com.reloadly.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.reloadly.transactionservice.utils.AppUtils.validateAndGetBearerToken;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping(path = "/deposit-funds")
    public ResponseEntity<ApiResponse<DepositResponse>> depositFund(@RequestHeader final HttpHeaders headers,
                                                                     @RequestBody @Valid final DepositRequestDto depositRequestDto) {
        log.info("initiate endpoint to deposit agent zone [{}] ::", depositRequestDto.getReceiver());
        String bearerToken = validateAndGetBearerToken(headers);

        DepositResponse response = transactionService.depositFunds(depositRequestDto,bearerToken);

        return ResponseEntity.ok(ApiResponse.<DepositResponse>builder()
                .isSuccessful(true)
                .statusMessage("funds deposited successfully")
                .data(response)
                .build()
        );

    }

    @PostMapping(path = "/transfer-funds")
    public ResponseEntity<ApiResponse<TransferResponseDto>> transferFund(@RequestHeader final HttpHeaders headers,
                                                                         @RequestBody @Valid final TransferRequestDto transferRequestDto) {
        log.info("initiate endpoint to transfer funds for [{}] ::", transferRequestDto.getReceiver());
        String bearerToken = validateAndGetBearerToken(headers);

        TransferResponseDto response = transactionService.transferFunds(transferRequestDto,bearerToken);
        return ResponseEntity.ok(ApiResponse.<TransferResponseDto>builder()
                .isSuccessful(true)
                .statusMessage("Transfer successfully")
                .data(response)
                .build()
        );
    }

    @PostMapping(path = "/withdraw-funds")
    public ResponseEntity<ApiResponse<WithdrawResponseDto>> withdrawFund(@RequestHeader final HttpHeaders headers,
                                                                         @RequestBody @Valid final WithdrawalRequestDto withdrawalRequestDto) {
        log.info("initiate endpoint to withdraw funds of [{}] ::", withdrawalRequestDto.getAmount());
        String bearerToken = validateAndGetBearerToken(headers);

        WithdrawResponseDto response = transactionService.withdrawFunds(withdrawalRequestDto,bearerToken);
        return ResponseEntity.ok(ApiResponse.<WithdrawResponseDto>builder()
                .isSuccessful(true)
                .statusMessage("Withdraw successfully")
                .data(response)
                .build()
        );
    }


}
