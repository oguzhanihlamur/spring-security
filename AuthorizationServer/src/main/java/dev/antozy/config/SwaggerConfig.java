package dev.antozy.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String CONTROLLER_PATH = "dev.antozy.controllers";

    @Value("spring.application.version")
    private String version;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot Authorization Server")
                        .version(version)
                        .description("API Documentation")
                        .contact(new Contact()
                                .name("Oğuzhan Ihlamur")
                                .email("oguzhanihlamur@gmail.com"))
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Spring Boot Authorization API Swagger UI"));
    }

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("all-api")
                .packagesToScan(CONTROLLER_PATH)
                .build();
    }

}
