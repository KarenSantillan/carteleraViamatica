package com.santillan.carteleraviamatica;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarteleraViamaticaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarteleraViamaticaApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI().info(new Info().title("Spring Boot API Cartelera").version("0.11").description("Sample app Spring Boot with Swagger").termsOfService("http://swagger.io/terms/").license(new License().name("Apache 2.2").url("http://springdoc.org")));
    }
}
