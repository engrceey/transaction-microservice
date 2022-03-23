package com.reloadly.transactionservice.utils;

import com.reloadly.transactionservice.dto.response.TransactionNotificationEmailDto;

public class EmailUtil {

    public static String transactionNotificationEmailBody(TransactionNotificationEmailDto transactionNotificationEmailDto) {
        return String.format(
          "Hi %s, The amount of #%s has been %s into your account by %s",
          transactionNotificationEmailDto.getReceiver(),
                transactionNotificationEmailDto.getAmount(),
                transactionNotificationEmailDto.getAction(),
                transactionNotificationEmailDto.getSender()
        );
    }

}
