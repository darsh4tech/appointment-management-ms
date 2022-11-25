package com.stc.appointmentmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class Swagger3Config {

	@Bean
	  public OpenAPI springShopOpenAPI() {
	      return new OpenAPI()
	              .info(new Info().title("Appointment Management")
	              .description("Appointment Management MS application")
	              .version("v_1.0.0")
	              .license(new License().name("Apache 2.0")))
	              .externalDocs(new ExternalDocumentation()
	              .description("Appointment MS"));
	  }

}
