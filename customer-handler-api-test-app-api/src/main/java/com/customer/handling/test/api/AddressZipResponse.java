package com.customer.handling.test.api;

import lombok.Builder;

@Builder


public record AddressZipResponse (
        String zipCode) {
}
