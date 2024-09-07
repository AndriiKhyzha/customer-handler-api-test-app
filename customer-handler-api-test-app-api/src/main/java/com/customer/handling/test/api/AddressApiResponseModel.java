package com.customer.handling.test.api;

import lombok.Builder;

@Builder
public record AddressApiResponseModel(
        Integer id,
        String country,
        String city,
        String street,
        String number) {
}