package br.com.microservice.payment.config;

import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public org.modelmapper.ModelMapper getModelMapper() {
        return new org.modelmapper.ModelMapper();
    }
}
