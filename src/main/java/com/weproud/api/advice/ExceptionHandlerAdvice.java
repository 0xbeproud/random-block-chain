package com.weproud.api.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.weproud.api.exception.ServiceException;
import com.weproud.api.response.ResponseBaseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @Autowired
    private Environment environment;


    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity handleServiceException(ServiceException e) {
        return ResponseBaseDto.serviceError(e);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(HttpServletRequest request, Exception e) throws JsonProcessingException, UnsupportedEncodingException, ExecutionException, InterruptedException {
        e.printStackTrace();
        return ResponseBaseDto.internalServerError(e.getMessage());
    }

    @Getter
    @Builder
    public static class MethodArgumentNotValidExceptionMessage {
        private String field;
        private Object rejectedValue;
        private String defaultMessage;
    }
}