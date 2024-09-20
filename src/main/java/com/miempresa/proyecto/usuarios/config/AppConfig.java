package com.miempresa.proyecto.usuarios.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.web.client.RestTemplate;



@Configuration
@EnableJpaRepositories(basePackages = "com.miempresa.proyecto.usuarios.repository")
@EntityScan(basePackages = "com.miempresa.proyecto.usuarios.model")
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
