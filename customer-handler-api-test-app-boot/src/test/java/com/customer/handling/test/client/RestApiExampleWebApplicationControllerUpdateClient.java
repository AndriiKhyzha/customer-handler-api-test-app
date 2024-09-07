package com.customer.handling.test.client;

import com.customer.handling.test.api.AddressApiRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.net.URI;

@RequiredArgsConstructor
public class RestApiExampleWebApplicationControllerUpdateClient {

    private final WebTestClient webTestClient;

    private final URI uri;

    public WebTestClient.ResponseSpec callTestRestControllerUpdate(AddressApiRequestModel addressApiRequestModel) {
        return webTestClient
                .mutate()
                .baseUrl(uri.toString())
                .build()
                .put()
                .uri("/address/update")
                .body(Mono.just(addressApiRequestModel), AddressApiRequestModel.class)
                .exchange();
    }
}
