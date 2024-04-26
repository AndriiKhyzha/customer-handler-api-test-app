package com.customer.handling.test.controller;

import com.customer.handling.test.api.AddressApiRequestModel;
import com.customer.handling.test.api.AddressApiResponseModel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
//MockitoExtension.class
public class AddressControllerTest {
    @Autowired
    private AddressController addressController;
    Integer id = 25;
    String country = "U.S.A.";
    String city = "Texas";
    String street = "Willy The Kid";
    String number = "85";
    String zipCode = "Get response from ";


    @Test
    void getAddressBody() {
//        given
        AddressApiRequestModel addressZipRequest = AddressApiRequestModel.builder()
                .id(id)
                .country(country)
                .city(city)
                .street(street)
                .number(number)
                .build();

//         when
//        AddressApiResponseModel addressApiResponseModel = AddressApiResponseModel.builder()
//                .status(zipCode + addressZipRequest)
//                .build();
//        when(addressController.getAddressBody(any(AddressApiRequestModel.class))).thenReturn(ResponseEntity.ok(addressApiResponseModel));
    }
}
