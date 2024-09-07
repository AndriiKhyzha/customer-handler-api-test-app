package com.customer.handling.test.annotation;

import com.customer.handling.test.boot.CustomerHandlerApiTestAppApplication;
import com.customer.handling.test.configuration.SpringTestConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith({
        SpringExtension.class})
@ContextConfiguration(classes = SpringTestConfiguration.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = CustomerHandlerApiTestAppApplication.class
)
@AutoConfigureWebTestClient
@AutoConfigureMockMvc
@ActiveProfiles({"integrationtest"})
public @interface IntegrationTest {
}
