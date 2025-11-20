package br.gov.Governamentais.controller;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * Libera o para acessar o no front-end
 */

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // aplica para todas as rotas
                        .allowedOrigins("http://localhost:63342") // front-end
                        .allowedOrigins("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedMethods("/**")
                        .allowedHeaders("*")
                        .allowedHeaders("/**");
            }
        };
    }
}
