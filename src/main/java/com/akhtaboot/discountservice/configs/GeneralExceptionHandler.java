package com.akhtaboot.discountservice.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class GeneralExceptionHandler {


    @ExceptionHandler({NoSuchElementException.class, NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleNoSuchElement(Exception e) {
        log.debug(e.getMessage(), e);
        return buildErrorResponse(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    }


    private static Map<String, Object> buildErrorResponse(HttpStatus status, String localizedMessage) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status.name());
        response.put("message", localizedMessage);
        return response;
    }
}