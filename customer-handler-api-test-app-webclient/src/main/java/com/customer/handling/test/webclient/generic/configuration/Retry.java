package com.customer.handling.test.webclient.generic.configuration;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record Retry(@NotNull Integer maxRetryAttempts,
                    @NotNull Integer minBackoffSeconds,
                    @NotNull Double jitterFactor,
                    @NotEmpty Integer[] statusList,
                    @NotEmpty Class<? extends Throwable>[] exceptionList) {
}
