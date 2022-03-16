package com.reloadly.transactionservice.service.implementation;

import com.reloadly.transactionservice.dto.request.TransferRequestDto;
import com.reloadly.transactionservice.dto.response.TransactionNoticeResponse;
import com.reloadly.transactionservice.exceptions.CustomException;
import com.reloadly.transactionservice.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {

    private final RestTemplate restTemplate;

    @Value("${mail.sender.base.url}")
    private String MAIL_SENDER_BASE_URL;

    @Override
    public TransactionNoticeResponse sendTransactionNotice(TransferRequestDto transferRequestDto) {
        try {
            return restTemplate.postForObject(MAIL_SENDER_BASE_URL,transferRequestDto, TransactionNoticeResponse.class);

        } catch (Exception exp) {
            log.error("exception occurred when sending mail :: message [{}]", exp.getMessage());
            throw new CustomException("exception occurred when sending mail");
        }
    }


}
