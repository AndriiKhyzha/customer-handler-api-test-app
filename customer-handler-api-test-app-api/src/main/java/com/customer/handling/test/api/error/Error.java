package com.customer.handling.test.api.error;

import lombok.Builder;

@Builder
public record Error(
        String code,
        String detail) {
}
