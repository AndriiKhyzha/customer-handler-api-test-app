package com.customer.handling.test.controller;

import com.customer.handling.test.api.AddressZipRequest;
import com.customer.handling.test.api.AddressZipResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
//@SpringBootTest

public class AddressZipControllerTest {
//    @Mock
    AddressZipController addressZipController;
    Integer id = 25;
    String country = "France";
    String city = "Paris";
    String street = "Willy The Kid";
    String number = "85";


//    @Test
    void getAddressBody() {
//        given
        AddressZipRequest addressZipRequest = AddressZipRequest.builder()
                .id(id)
                .country(country)
                .city(city)
                .street(street)
                .number(number)
                .build();

//         when
        AddressZipResponse addressZipResponse = AddressZipResponse.builder()
                .zipCode(String.valueOf(addressZipRequest.hashCode()))
                .build();
        when(addressZipController.getAddressBody(any(AddressZipRequest.class))).thenReturn(ResponseEntity.ok(addressZipResponse));
    }
}
