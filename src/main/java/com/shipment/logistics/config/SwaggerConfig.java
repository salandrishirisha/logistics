package com.shipment.logistics.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI shipmentOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Shipment Logistics API")
                        .description("Full Stack Shipment Logistics Management System APIs")
                        .version("1.0")
                        .contact(new Contact()
                                .name("salandri shirisha")
                                .email("shireesha22@gmail.com")));
    }
}