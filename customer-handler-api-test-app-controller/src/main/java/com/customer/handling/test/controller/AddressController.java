package com.customer.handling.test.controller;

import com.customer.handling.service.api.AddressData;
import com.customer.handling.test.api.AddressApiRequestModel;
import com.customer.handling.test.api.AddressApiResponseModel;
import com.customer.handling.test.controller.exception.RequestNotValidException;
import com.customer.handling.test.controller.mapper.ControllerMapper;
import com.customer.handling.test.service.WebClientTestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/address")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddressController {

    private final WebClientTestService webClientTestService;

    private final ControllerMapper controllerMapper;


    @ResponseBody
    @Tag(name = "post", description = "create AddressData body in My SQL DB with help Swagger:-P")
    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressApiResponseModel> postAddressBody(@Valid @RequestBody AddressApiRequestModel addressApiRequestModel) {
        AddressData addressDataResponse;
        try {
            addressDataResponse = webClientTestService
                    .httpMethodPost(controllerMapper.map(addressApiRequestModel));
        } catch (RequestNotValidException e) {
            throw new RuntimeException(e.getMessage());
        }
        return ResponseEntity.ok(controllerMapper.map(addressDataResponse));
    }

    @ResponseBody
    @Tag(name = "get", description = "get AddressData body from MySQL DB by {/id} with help Swagger:-P")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressApiResponseModel> getAddressBody(@Valid @PathVariable("id") Integer id) {
        if (id <= 0) {
            throw new RequestNotValidException("Id must not be negative or Zero");
        }

        AddressApiResponseModel addressApiResponseModel = controllerMapper.map(webClientTestService.httpMethodGet(id));

        return ResponseEntity.ok(addressApiResponseModel);
    }

    @ResponseBody
    @Tag(name = "update", description = "update AddressData body in MySQL DB")
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressApiResponseModel> updateAddressBody(@Valid @RequestBody AddressApiRequestModel addressApiRequestModel) {
        if (addressApiRequestModel.id() <= 0) {
            throw new RequestNotValidException("Id must not be negative or Zero");
        }
        AddressData addressData = webClientTestService.httpMethodPut(controllerMapper.map(addressApiRequestModel));
        return ResponseEntity.ok(controllerMapper.map(addressData));
    }

    @Tag(name = "delete", description = "delete AddressData body in MySQL DB")
    @DeleteMapping(value = "/{id}")
    public void deleteAddressBody(@PathVariable("id")  Integer id) {
        if (id <= 0) {
            throw new RequestNotValidException("Id value must not be negative or Zero");
        }
        webClientTestService.httpMethodDelete(id);
    }
}