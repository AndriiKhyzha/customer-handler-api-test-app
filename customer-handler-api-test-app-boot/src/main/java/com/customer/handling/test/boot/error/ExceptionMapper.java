package com.customer.handling.test.boot.error;

import com.customer.handling.service.api.error.Error;
import com.customer.handling.service.api.error.ErrorResponse;
import com.customer.handling.test.service.exception.ItemCreationException;
import com.customer.handling.test.service.exception.ItemIsNotPresentException;
import com.customer.handling.test.controller.exception.RequestNotValidException;
import com.customer.handling.test.service.exception.ItemUpdateException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler(RequestNotValidException.class)
    public ResponseEntity<ErrorResponse> handleRequestNotValidException(
            HttpServletRequest httpServletRequest,
            RequestNotValidException requestNotValidException) {

        log.error(requestNotValidException.getMessage());

        return createResponseEntity(
                HttpStatus.BAD_REQUEST,
                httpServletRequest.getRequestURI(),
                "CODE",
                requestNotValidException.getMessage()
        );
    }

    @ExceptionHandler(ItemIsNotPresentException.class)
    public ResponseEntity<ErrorResponse> handleItemNotFoundException(
            HttpServletRequest httpServletRequest,
            ItemIsNotPresentException itemIsNotPresentException) {

        log.error(itemIsNotPresentException.getMessage());

        return createResponseEntity(
                HttpStatus.BAD_REQUEST,
                httpServletRequest.getRequestURI(),
                "CODE2!",
                itemIsNotPresentException.getMessage()
        );
    }

    @ExceptionHandler(ItemCreationException.class)
    public ResponseEntity<ErrorResponse> handleItemCreationException(
            HttpServletRequest httpServletRequest,
            ItemCreationException itemCreationException) {

        log.error(itemCreationException.getMessage());

        return createResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR,
                httpServletRequest.getRequestURI(),
                "CODE3!",
                itemCreationException.getMessage()
        );
    }

    @ExceptionHandler(ItemUpdateException.class)
    public ResponseEntity<ErrorResponse> handleItemUpdateException(
            HttpServletRequest httpServletRequest,
            ItemUpdateException itemUpdateException) {

        log.error(itemUpdateException.getMessage());

        return createResponseEntity(
                HttpStatus.INTERNAL_SERVER_ERROR,
                httpServletRequest.getRequestURI(),
                "CODE4!",
                itemUpdateException.getMessage()
        );
    }

    private ResponseEntity<ErrorResponse> createResponseEntity(
            HttpStatus httpStatus,
            String requestUri,
            String code,
            String detail) {

        return ResponseEntity
                .status(httpStatus)
                .body(
                        ErrorResponse.builder()
                                .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                                .path(requestUri)
                                .errors(List.of(
                                        Error.builder()
                                                .code(code)
                                                .detail(detail)
                                                .build()
                                ))
                                .build()
                );
    }
}
