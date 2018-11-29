package com.example.microserviceschool;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class StashErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 500 && response.status() <= 599) {
            return new HttpServerErrorException(HttpStatus.valueOf(response.status()));
        }
        //return defaultErrorDecoder.decode(methodKey,response);
        return defaultErrorDecoder.decode(methodKey,response);
    }
}
