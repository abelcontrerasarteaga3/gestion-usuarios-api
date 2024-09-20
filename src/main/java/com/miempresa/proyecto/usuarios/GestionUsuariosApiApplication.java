package com.miempresa.proyecto.usuarios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.Filter;

@SpringBootApplication
public class GestionUsuariosApiApplication extends SpringBootServletInitializer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        SpringApplication.run(GestionUsuariosApiApplication.class, args);
    }

    @Bean
	public WebMvcConfigurer corsConfigurer() {  
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				//logger.warn("-->addCorsMappings");
				registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET","POST","HEAD","PUT","DELETE","OPTIONS","TRACE","PATCH")
				.allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization");
			}
		};
	}
	
	@Bean
	public Filter getCharacterEncodingFilter() {
	    org.springframework.web.filter.CharacterEncodingFilter characterEncodingFilter = new org.springframework.web.filter.CharacterEncodingFilter();
	    characterEncodingFilter.setEncoding("UTF-8");
	    characterEncodingFilter.setForceEncoding(true);
	    return characterEncodingFilter;
	}
}
