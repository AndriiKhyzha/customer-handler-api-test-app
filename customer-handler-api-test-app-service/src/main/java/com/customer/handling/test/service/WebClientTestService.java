package com.customer.handling.test.service;

import com.customer.handling.service.api.AddressData;
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


    public void httpMethodPost(AddressServiceModel addressServiceModel) {
        String body;
        try {
            AddressData addressData = serviceMapper.map(addressServiceModel);
            body = objectMapper.writeValueAsString(addressData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
        Optional<AddressData> addressDataOptional = genericWebClient.send(HttpMethod.POST, "http://localhost:8080/address/create", body, AddressData.class);
        if (addressDataOptional.isPresent()) {
            System.out.println("Created body " + body);
        }
    }

    public AddressServiceModel httpMethodGet(Integer id) {
        Optional<AddressData> addressDataOptional = genericWebClient.sendGet("http://localhost:8080/address/" + id, AddressData.class);
        addressDataOptional.ifPresent(addressData -> System.out.println("Get Address body with  id: " + id + addressData));
        return serviceMapper.map(addressDataOptional.get());
    }

    public void httpMethodPut(AddressServiceModel addressServiceModel) {
        String body;
        try {
            AddressData addressData = serviceMapper.map(addressServiceModel);
            body = objectMapper.writeValueAsString(addressData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
        Optional<AddressData> addressDataOptional = genericWebClient.send(HttpMethod.PUT, "http://localhost:8080/address/update", body, AddressData.class);
        if (addressDataOptional.isPresent()) {
            System.out.println("Put body " + body);
        }
    }
    public void httpMethodDelete(Integer id) {
        genericWebClient.sendDelete("http://localhost:8080/address/" + id);
    }
}