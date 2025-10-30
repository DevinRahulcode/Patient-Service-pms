package com.example.patient_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Patient Service API")
                        .description("REST API for managing Patient Service.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Devin Rahul")))
                .addTagsItem(new Tag().name("Patient").description("Endpoints for the do the crud operations"));

    }
}
