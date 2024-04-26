package com.customer.handling.test.boot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SwaggerConfiguration {
    @Value("${app.api.description}")
    private String description;
    @Value("${app.api.title}")
    private String title;
    @Value("${app.api.version}")
    private String version;

    @Bean
    public OpenAPI baseOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                .title(title)
                .version(version)
                .description(description));
    }
}
