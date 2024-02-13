package com.customer.handling.test.boot;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = "com.customer.handling.test")
public class CustomerHandlerApiTestAppApplicationConfiguration {
}
