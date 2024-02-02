package com.customer.handling.test.boot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Component
@RestControllerAdvice // this annotation add logging to rest controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RestRequestLogger {

    @Value("${customer-handler-api-test-app.logging.logging-uri-patterns-list}")
    private List<String> loggingUriPatternsList;

    private final ObjectMapper objectMapper;

    @InitBinder
    private void initBinder(ServletWebRequest servletWebRequest, WebDataBinder webDataBinder) {
        HttpServletRequest httpServletRequest = servletWebRequest.getRequest();

        if (loggingUriPatternsList.stream()
                .anyMatch(httpUri -> Pattern.compile(httpUri).matcher(httpServletRequest.getRequestURI()).find())) {

            log.info("Controller received request: \n{} {}\n{}",
                    httpServletRequest.getMethod(),
                    httpServletRequest.getRequestURI(),
                    getBodyFrom(webDataBinder, httpServletRequest)
            );
        }
    }

    private String getBodyFrom(WebDataBinder webDataBinder, HttpServletRequest httpServletRequest) {
        String body = "";

        if (!HttpMethod.GET.name().equals(httpServletRequest.getMethod())) {
            try {
                body = objectMapper.writeValueAsString(webDataBinder.getTarget());
            } catch (JsonProcessingException exception) {
                body = "Fail to parse request body: " + exception;
            }
        }

        return body;
    }
}