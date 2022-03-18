package com.reloadly.transactionservice.utils;

import com.reloadly.transactionservice.exceptions.CustomException;
import com.reloadly.transactionservice.exceptions.RemoteErrorHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
public class AppUtils {

    private static final RestTemplate restTemplate = new RestTemplateBuilder()
            .errorHandler(new RemoteErrorHandler())
            .build();

    public static String validateAndGetBearerToken(HttpHeaders headers) {
        log.info("::: Validating auth token :::");
        final String authToken = headers.containsKey("Authorization") ? headers.get("Authorization").get(0) : "";

        if (authToken.isBlank()) {
            log.error("::: Auth token is empty :::");
            throw new CustomException("Please provide a valid authentication token.", HttpStatus.BAD_REQUEST);
        }

        return authToken.startsWith("Bearer ") ? authToken : "Bearer " + authToken;
    }

    public static <T> Response<T> doApiCall(String url, HttpMethod httpMethod, HttpEntity httpEntity, T instance) {
        log.info("::: Making external REST call to URL: [{}]:::", url);
        ResponseEntity response =  restTemplate.exchange(url, httpMethod, httpEntity, instance.getClass());
        return new Response<T>(response.getStatusCodeValue(), (T) response.getBody());
    }

    @Data
    @AllArgsConstructor
    public static class Response<T> {
        private int code;
        private T body;
    }

}
