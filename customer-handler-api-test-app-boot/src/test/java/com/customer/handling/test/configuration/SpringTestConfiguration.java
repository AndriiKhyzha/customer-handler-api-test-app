package com.customer.handling.test.configuration;

import com.customer.handling.test.client.RestApiExampleWebApplicationControllerDeleteClient;
import com.customer.handling.test.client.RestApiExampleWebApplicationControllerGetClient;
import com.customer.handling.test.client.RestApiExampleWebApplicationControllerPostClient;
import com.customer.handling.test.client.RestApiExampleWebApplicationControllerUpdateClient;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

@TestConfiguration
@ComponentScan(basePackages = {
        "com.customer.handling.test"
})
public class SpringTestConfiguration {

    @Bean
    @Lazy
    public RestApiExampleWebApplicationControllerPostClient testControllerPostWebClient(WebTestClient webTestClient,
                                                                                        @LocalServerPort Integer localServerPort,
                                                                                        @Value("${test.scheme}") String scheme,
                                                                                        @Value("${test.host})") String host)
                throws URISyntaxException {

            return new RestApiExampleWebApplicationControllerPostClient(
                    webTestClient,
                    new URIBuilder()
                            .setCharset(StandardCharsets.UTF_8)
                            .setScheme(scheme)
                            .setHost(host)
                            .setPort(localServerPort)
                            .build()
            );
    }

    @Bean
    @Lazy
    public RestApiExampleWebApplicationControllerGetClient testControllerGetWebClient(WebTestClient webTestClient,
                                                                                      @LocalServerPort Integer localServerPort,
                                                                                      @Value("${test.scheme}") String scheme,
                                                                                      @Value("${test.host}") String host)
            throws URISyntaxException {

        return new RestApiExampleWebApplicationControllerGetClient(
                webTestClient,
                new URIBuilder()
                        .setCharset(StandardCharsets.UTF_8)
                        .setScheme(scheme)
                        .setHost(host)
                        .setPort(localServerPort)
                        .build()
        );
    }

    @Bean
    @Lazy
    public RestApiExampleWebApplicationControllerUpdateClient testControllerUpdateWebClient(WebTestClient webTestClient,
                @LocalServerPort Integer localServerPort,
                @Value("${test.scheme}") String scheme,
                @Value("${test.host}") String host)
            throws URISyntaxException {

        return new RestApiExampleWebApplicationControllerUpdateClient(
                webTestClient,
                new URIBuilder()
                        .setCharset(StandardCharsets.UTF_8)
                        .setScheme(scheme)
                        .setHost(host)
                        .setPort(localServerPort)
                        .build()
        );

    }

    @Bean
    @Lazy
    public RestApiExampleWebApplicationControllerDeleteClient testControllerDeleteWebClient(WebTestClient webTestClient,
                                                                                            @LocalServerPort Integer localServerPort,
                                                                                            @Value("${test.scheme}") String scheme,
                                                                                            @Value("${test.host}") String host)
            throws URISyntaxException {

        return new RestApiExampleWebApplicationControllerDeleteClient(
                webTestClient,
                new URIBuilder()
                        .setCharset(StandardCharsets.UTF_8)
                        .setScheme(scheme)
                        .setHost(host)
                        .setPort(localServerPort)
                        .build()
        );
    }
}