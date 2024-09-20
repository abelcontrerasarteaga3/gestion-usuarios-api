package com.miempresa.proyecto.usuarios.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.miempresa.proyecto.usuarios.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo())
				;
	}


	private ApiInfo getApiInfo() {
		return new ApiInfo("Gestión de Usuarios API", "API para la gestión de usuarios", "1.0",
				"http://www.evaluacion.com/",
				new Contact("Abel Contreras Arteaga", "https://www.evaluacion.com", "abelcontrerasarteaga3@gmail.com"),
				"LICENSE", "LICENSE URL", Collections.emptyList());
	}
}
