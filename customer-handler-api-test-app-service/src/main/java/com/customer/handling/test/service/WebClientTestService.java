package com.customer.handling.test.service;

import com.customer.handling.service.api.AddressData;
import com.customer.handling.test.service.exception.ItemCreationException;
import com.customer.handling.test.service.exception.ItemIsNotPresentException;
import com.customer.handling.test.service.exception.ItemUpdateException;
import com.customer.handling.test.service.mapper.ServiceMapper;
import com.customer.handling.test.service.model.AddressServiceModel;
import com.customer.handling.test.webclient.generic.GenericWebClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WebClientTestService {

    private final GenericWebClient genericWebClient;
    private final ObjectMapper objectMapper;
    private final ServiceMapper serviceMapper;

    public AddressData httpMethodPost(AddressServiceModel addressServiceModel) {
        String body;
        try {
            AddressData addressData = serviceMapper.map(addressServiceModel);
            body = objectMapper.writeValueAsString(addressData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
        Optional<AddressData> addressDataOptional = genericWebClient
                .send(HttpMethod.POST, "http://localhost:8080/address/create", body, AddressData.class);
        return addressDataOptional
                .orElseThrow(() -> new ItemCreationException("Error with create data from webclient"));
    }

    public AddressServiceModel httpMethodGet(Integer id) {
        AddressData addressData = genericWebClient
                .sendGet("http://localhost:8080/address/" + id, AddressData.class)
                .orElseThrow(() -> new ItemIsNotPresentException("Address Body by " + id + " wasn't found"));
        return serviceMapper.map(addressData);
    }

    public AddressData httpMethodPut(AddressServiceModel addressServiceModel) {
        String body;
        try {
            AddressData addressData = serviceMapper.map(addressServiceModel);
            body = objectMapper.writeValueAsString(addressData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
        Optional<AddressData> addressDataOptional = genericWebClient
                .send(HttpMethod.PUT, "http://localhost:8080/address/update", body, AddressData.class);
        return addressDataOptional
                .orElseThrow(() -> new ItemUpdateException("Error with update data from webclient"));
    }
    public void httpMethodDelete(Integer id) {
        if (id <= 0) {
            throw new ItemIsNotPresentException("Id value must not be negative or Zero");
        }
        genericWebClient.sendDelete("http://localhost:8080/address/" + id);
    }
}