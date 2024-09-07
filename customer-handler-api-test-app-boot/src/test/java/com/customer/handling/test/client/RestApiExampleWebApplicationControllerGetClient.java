package com.customer.handling.test.client;

import lombok.RequiredArgsConstructor;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.net.URI;

@RequiredArgsConstructor
public class RestApiExampleWebApplicationControllerGetClient {

    private final WebTestClient webTestClient;
    private final URI uri;

    public WebTestClient.ResponseSpec callTestRestControllerGet(String id) {
        return webTestClient
                .mutate()
                .baseUrl(uri.toString())
                .build()
                .get()
                .uri("/address/" + id)
                .exchange();
    }
}
