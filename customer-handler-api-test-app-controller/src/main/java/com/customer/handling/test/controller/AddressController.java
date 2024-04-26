package com.customer.handling.test.controller;

import com.customer.handling.test.api.AddressApiRequestModel;
import com.customer.handling.test.api.AddressApiResponseModel;
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
        webClientTestService.httpMethodPost(controllerMapper.map(addressApiRequestModel));
        return ResponseEntity.ok(AddressApiResponseModel.builder()
                .status("Suka, idy sudy!!")
                .build());
    }

    @ResponseBody
    @Tag(name = "get", description = "get AddressData body from MySQL DB by {/id} with help Swagger:-P")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressApiRequestModel> getAddressBody(@Valid @PathVariable("id") Integer id) {
        return ResponseEntity.ok(controllerMapper.map(webClientTestService.httpMethodGet(id)));
    }

    @ResponseBody
    @Tag(name = "update", description = "update AddressData body in MySQL DB")
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressApiResponseModel> updateAddressBody(@Valid @RequestBody AddressApiRequestModel addressApiRequestModel) {
        webClientTestService.httpMethodPut(controllerMapper.map(addressApiRequestModel));
        return ResponseEntity.ok(AddressApiResponseModel.builder()
                .status("ok")
                .build());
    }

    @Tag(name = "delete", description = "delete AddressData body in MySQL DB")
    @DeleteMapping(value = "/{id}")
    public void deleteAddressBody(@PathVariable("id")  Integer id) {
        webClientTestService.httpMethodDelete(id);
    }
}