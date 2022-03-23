package com.reloadly.transactionservice.service.implementation;

import com.reloadly.transactionservice.dto.request.EmailRequestDto;
import com.reloadly.transactionservice.dto.response.TransactionNotificationEmailDto;
import com.reloadly.transactionservice.exceptions.CustomException;
import com.reloadly.transactionservice.service.MailSenderService;
import com.reloadly.transactionservice.utils.EmailUtil;
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
    public void sendDepositTransactionNotice(TransactionNotificationEmailDto transactionNotificationEmailDto) {
        try {
            EmailRequestDto mailObj = new EmailRequestDto();
            transactionNotificationEmailDto.setAction("deposited");
            mailObj.setSender(transactionNotificationEmailDto.getSender());
            mailObj.setBody(EmailUtil.transactionNotificationEmailBody(transactionNotificationEmailDto));
            mailObj.setSubject("Deposit notice");
            mailObj.setRecipient(transactionNotificationEmailDto.getReceiverEmail());

            restTemplate.postForObject(MAIL_SENDER_BASE_URL, mailObj, void.class);

        } catch (Exception exp) {
            log.error("exception occurred when sending mail :: message [{}]", exp.getMessage());
            throw new CustomException("exception occurred when sending mail");
        }
    }

    @Override
    public void sendTransferTransactionNotice(TransactionNotificationEmailDto transactionNotificationEmailDto) {
        try {
            EmailRequestDto mailObj = new EmailRequestDto();
            transactionNotificationEmailDto.setAction("transferred");
            mailObj.setSender(transactionNotificationEmailDto.getSender());
            mailObj.setBody(EmailUtil.transactionNotificationEmailBody(transactionNotificationEmailDto));
            mailObj.setSubject("Fund Transfer Notice");
            mailObj.setRecipient(transactionNotificationEmailDto.getReceiverEmail());

            restTemplate.postForObject(MAIL_SENDER_BASE_URL, mailObj, void.class);

        } catch (Exception exp) {
            log.error("exception occurred when sending mail :: message [{}]", exp.getMessage());
            throw new CustomException("exception occurred when sending mail");
        }
    }

    @Override
    public void sendWithdrawalTransactionNotice(TransactionNotificationEmailDto transactionNotificationEmailDto) {
        try {
            EmailRequestDto mailObj = new EmailRequestDto();
            transactionNotificationEmailDto.setAction("withdraw");
            mailObj.setSender(transactionNotificationEmailDto.getSender());
            mailObj.setBody(EmailUtil.transactionNotificationEmailBody(transactionNotificationEmailDto));
            mailObj.setSubject("Withdrawal Notice");
            mailObj.setRecipient(transactionNotificationEmailDto.getReceiverEmail());

            restTemplate.postForObject(MAIL_SENDER_BASE_URL, mailObj, void.class);

        } catch (Exception exp) {
            log.error("exception occurred when sending mail :: message [{}]", exp.getMessage());
            throw new CustomException("exception occurred when sending mail");
        }
    }


}
