package com.customer.handling.test.api;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Builder
@Validated
public record AddressZipRequest(
        @NotNull
        Integer id,
        @NotNull
        String country,
        @NotNull
        String city,
        @NotNull
        String street,
        @NotNull
        String number
) {

}