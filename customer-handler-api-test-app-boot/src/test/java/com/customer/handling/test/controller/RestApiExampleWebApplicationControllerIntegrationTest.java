package com.customer.handling.test.controller;

import com.customer.handling.service.api.AddressData;
import com.customer.handling.service.test.utils.TestUtils;
import com.customer.handling.test.annotation.IntegrationTest;
import com.customer.handling.test.api.AddressApiRequestModel;
import com.customer.handling.test.api.AddressApiResponseModel;
import com.customer.handling.test.api.error.ErrorResponse;
import com.customer.handling.test.client.RestApiExampleWebApplicationControllerDeleteClient;
import com.customer.handling.test.client.RestApiExampleWebApplicationControllerGetClient;
import com.customer.handling.test.client.RestApiExampleWebApplicationControllerPostClient;
import com.customer.handling.test.client.RestApiExampleWebApplicationControllerUpdateClient;
import com.customer.handling.test.webclient.generic.GenericWebClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@IntegrationTest
public class RestApiExampleWebApplicationControllerIntegrationTest {

    @Autowired
    private RestApiExampleWebApplicationControllerGetClient restApiExampleWebApplicationControllerGetClient;

    @Autowired
    private RestApiExampleWebApplicationControllerUpdateClient restApiExampleWebApplicationControllerUpdateClient;

    @Autowired
    private RestApiExampleWebApplicationControllerDeleteClient restApiExampleWebApplicationControllerDeleteClient;

    @Autowired
    private RestApiExampleWebApplicationControllerPostClient restApiExampleWebApplicationControllerPostClient;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GenericWebClient genericWebClient;

    //  post
    @Test
    void testControllerPostWebClient_validData_returnOk() throws JsonProcessingException {
        //  when
        when(genericWebClient.send(
                eq(HttpMethod.POST),
                eq("http://localhost:8080/address/create"),
                eq(objectMapper.writeValueAsString(
                                TestUtils.readValue(
                                        this.getClass(),
                                        AddressData.class,
                                        "json-objects/test-post-web-client-request-body-mock-1.json"))
                ),
                eq(AddressData.class)))
                .thenReturn(Optional.of(TestUtils.readValue(
                        this.getClass(),
                        AddressData.class,
                        "json-objects/test-post-web-client-response-body-mock-1.json")));

        WebTestClient.ResponseSpec responseSpec = restApiExampleWebApplicationControllerPostClient
                .callTestRestControllerPost(TestUtils.readValue(
                        this.getClass(),
                        AddressApiRequestModel.class,
                        "json-objects/test-post-controller-request.json")
                );

        //  then
        responseSpec.expectStatus().isEqualTo(HttpStatus.OK);

        responseSpec.expectBody(AddressApiResponseModel.class)
                .isEqualTo(TestUtils.readValue(
                        this.getClass(),
                        AddressApiResponseModel.class,
                        "json-objects/test-post-controller-response-expected.json")
                );
    }

    @Test
    void testControllerPostWebClient_webClientReturnsNull_returnInternalServerError() throws JsonProcessingException {
        //  when
        when(genericWebClient.send(
                eq(HttpMethod.POST),
                eq("http://localhost:8080/address/create"),
                eq(objectMapper.writeValueAsString(
                        TestUtils.readValue(
                                this.getClass(),
                                AddressData.class,
                                "json-objects/test-post-web-client-request-body-mock-1.json"))
                ),
                eq(AddressData.class)))
                .thenReturn(Optional.empty());

        WebTestClient.ResponseSpec responseSpec = restApiExampleWebApplicationControllerPostClient
                .callTestRestControllerPost(TestUtils.readValue(
                        this.getClass(),
                        AddressApiRequestModel.class,
                        "json-objects/test-post-controller-request.json")
                );

        //  then
        ErrorResponse errorResponse = responseSpec
                .expectBody(ErrorResponse.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(errorResponse);
        assertEquals("/address/post", errorResponse.path());
        assertEquals("CODE3!", errorResponse.errors().get(0).code());
        assertEquals("Error with create data from webclient", errorResponse.errors().get(0).detail());
        assertNotNull(errorResponse.timestamp());

        responseSpec
                .expectStatus()
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //  update
    @Test
    void testControllerUpdateWebClient_validData_returnOk() throws JsonProcessingException {
        // when
        when(genericWebClient.send(
                eq(HttpMethod.PUT),
                eq("http://localhost:8080/address/update"),
                eq(objectMapper.writeValueAsString(
                        TestUtils.readValue(
                        this.getClass(),
                        AddressData.class,
                        "json-objects/test-update-web-client-request-body-mock.json"))),
                eq(AddressData.class)))
                .thenReturn(Optional.of(TestUtils.readValue(this.getClass(),
                        AddressData.class,
                        "json-objects/test-update-web-client-response-body-mock.json")
                ));

        WebTestClient.ResponseSpec responseSpec = restApiExampleWebApplicationControllerUpdateClient
                .callTestRestControllerUpdate(TestUtils.readValue(
                        this.getClass(),
                        AddressApiRequestModel.class,
                        "json-objects/test-update-controller-request.json"
                ));

        // then
        responseSpec.expectStatus().isEqualTo(HttpStatus.OK);
        responseSpec.expectBody(AddressApiResponseModel.class).isEqualTo(TestUtils.readValue(
                this.getClass(),
                AddressApiResponseModel.class,
                "json-objects/test-update-controller-response.json"
        ));
    }

    @Test
    void testControllerUpdateWebClient_InvalidId_ReturnBadRequest() throws JsonProcessingException {
        //  when
        WebTestClient.ResponseSpec responseSpec = restApiExampleWebApplicationControllerUpdateClient
                .callTestRestControllerUpdate(TestUtils.readValue(
                        this.getClass(),
                        AddressApiRequestModel.class,
                        "json-objects/test-update-web-client-request-body-mock-invalid-id.json"
                ));

        //  then
        ErrorResponse errorResponse = responseSpec
                .expectBody(ErrorResponse.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(errorResponse);
        assertEquals("/address/update", errorResponse.path());
        assertEquals("CODE", errorResponse.errors().get(0).code());
        assertEquals("Id must not be negative or Zero", errorResponse.errors().get(0).detail());
        assertNotNull(errorResponse.timestamp());

        responseSpec
                .expectStatus()
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testControllerUpdateWebClient_webClientReturnsNull_returnInternalServerError() throws JsonProcessingException {
        // when
        when(genericWebClient.send(
                eq(HttpMethod.PUT),
                eq("http://localhost:8080/address/update"),
                eq(objectMapper.writeValueAsString(
                        TestUtils.readValue(
                                this.getClass(),
                                AddressData.class,
                                "json-objects/test-update-web-client-request-body-mock.json"))),
                eq(AddressData.class)))
                .thenReturn(Optional.empty());

        WebTestClient.ResponseSpec responseSpec = restApiExampleWebApplicationControllerUpdateClient
                .callTestRestControllerUpdate(TestUtils.readValue(
                        this.getClass(),
                        AddressApiRequestModel.class,
                        "json-objects/test-update-controller-request.json"
                ));

        // then
        ErrorResponse errorResponse = responseSpec
                .expectBody(ErrorResponse.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(errorResponse);
        assertEquals("/address/update", errorResponse.path());
        assertEquals("CODE4!", errorResponse.errors().get(0).code());
        assertEquals("Error with update data from webclient", errorResponse.errors().get(0).detail());
        assertNotNull(errorResponse.timestamp());

        responseSpec
                .expectStatus()
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //  get
    @Test
    void testControllerGetWebClient_invalidId_returnBadRequest() {
        //  when
        WebTestClient.ResponseSpec responseSpec = restApiExampleWebApplicationControllerGetClient
            .callTestRestControllerGet("-1");

        //  then
        ErrorResponse errorResponse = responseSpec
                .expectBody(ErrorResponse.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(errorResponse);
        assertEquals("/address/-1", errorResponse.path());
        assertEquals("CODE", errorResponse.errors().get(0).code());
        assertEquals("Id must not be negative or Zero", errorResponse.errors().get(0).detail());
        assertNotNull(errorResponse.timestamp());

        responseSpec
                .expectStatus()
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testControllerGetWebClient_idIsInvalid_returnOk() throws RuntimeException {
        //  when
        when(genericWebClient.sendGet(
                eq("http://localhost:8080/address/1"),
                eq(AddressData.class)))
                .thenReturn(Optional.of(TestUtils.readValue(
                        this.getClass(),
                        AddressData.class,
                        "json-objects/test-get-web-client-request-body-mock-dbId1.json"
                )));

        WebTestClient.ResponseSpec responseSpec = restApiExampleWebApplicationControllerGetClient
                .callTestRestControllerGet("1");

        //  then
        responseSpec.expectStatus()
                .isEqualTo(HttpStatus.OK);
        responseSpec.expectBody(AddressApiResponseModel.class).isEqualTo(TestUtils.readValue(
                this.getClass(),
                AddressApiResponseModel.class,
                "json-objects/test-get-web-client-response-body.json"
        ));
    }

    @Test
    void testControllerGetWebClient_webClientReturnNull_returnBadRequest() {
        //  when
        when(genericWebClient.sendGet(
                eq("http://localhost:8080/address/1"),
                eq(AddressData.class)))
                .thenReturn(Optional.empty());

        WebTestClient.ResponseSpec responseSpec = restApiExampleWebApplicationControllerGetClient
                .callTestRestControllerGet("1");

        //  then
        ErrorResponse errorResponse = responseSpec
                .expectBody(ErrorResponse.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(errorResponse);
        assertEquals("/address/1", errorResponse.path());
        assertEquals("CODE2!", errorResponse.errors().get(0).code());
        assertEquals("Address Body by 1 wasn't found", errorResponse.errors().get(0).detail());
        assertNotNull(errorResponse.timestamp());

        responseSpec
                .expectStatus()
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    //  delete
    @Test
    void testControllerDeleteWebClient_invalidId_returnErrorResponse() {
        //  when
        WebTestClient.ResponseSpec responseSpec = restApiExampleWebApplicationControllerDeleteClient
                .callTestRestControllerDelete("-1");

        //  then
        ErrorResponse errorResponse = responseSpec
                .expectBody(ErrorResponse.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(errorResponse);
        assertEquals("/address/-1", errorResponse.path());
        assertEquals("CODE", errorResponse.errors().get(0).code());
        assertEquals("Id value must not be negative or Zero", errorResponse.errors().get(0).detail());
        assertNotNull(errorResponse.timestamp());

        responseSpec
                .expectStatus()
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testControllerDeleteWebClient_validId_returnOk() {
        //  when
        WebTestClient.ResponseSpec responseSpec = restApiExampleWebApplicationControllerDeleteClient
                .callTestRestControllerDelete("1");

        //  then
        responseSpec
                .expectStatus()
                .isEqualTo(HttpStatus.OK);
    }
}