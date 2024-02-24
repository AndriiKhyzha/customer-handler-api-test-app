package com.customer.handling.test.controller;

import com.customer.handling.test.api.AddressApiRequestModel;
import com.customer.handling.test.api.AddressApiResponseModel;
import com.customer.handling.test.controller.mapper.ControllerMapper;
import com.customer.handling.test.service.WebClientTestService;
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
    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressApiResponseModel> getAddressBody(@Valid @RequestBody AddressApiRequestModel addressApiRequestModel) {
            webClientTestService.testWebClient(controllerMapper.map(addressApiRequestModel));
            return ResponseEntity.ok(AddressApiResponseModel.builder()
                    .status("ok")
                    .build());
    }
}