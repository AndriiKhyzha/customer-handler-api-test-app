package com.customer.handling.test.client;

import com.customer.handling.test.api.AddressApiRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.net.URI;

@RequiredArgsConstructor
public class RestApiExampleWebApplicationControllerPostClient {

    private final WebTestClient webTestClient;
    private final URI uri;

    public WebTestClient.ResponseSpec callTestRestControllerPost(AddressApiRequestModel restApiExampleModel) {
        return webTestClient
                .mutate()
                .baseUrl(uri.toString())
                .build()
                .post()
                .uri("/address/post")
                .body(Mono.just(restApiExampleModel), AddressApiRequestModel.class)
                .exchange();
    }
}
