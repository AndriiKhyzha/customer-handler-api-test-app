package com.customer.handling.test.controller;

import com.customer.handling.test.api.AddressZipRequest;
import com.customer.handling.test.api.AddressZipResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/address")
public class AddressZipController {

    @ResponseBody
    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressZipResponse> getAddressBody(@Valid @RequestBody AddressZipRequest addressZipRequest) {
            AddressZipResponse addressZipResponse = AddressZipResponse.builder()
                    .zipCode("Get response from " + addressZipRequest)
                    .build();
            log.info(String.valueOf(addressZipResponse));
            return ResponseEntity.ok(addressZipResponse);
    }
}