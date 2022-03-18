package com.reloadly.transactionservice.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Slf4j
public class RemoteErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

    }
}