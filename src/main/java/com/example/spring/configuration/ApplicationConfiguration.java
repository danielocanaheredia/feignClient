package com.example.spring.configuration;

import com.example.service.FindByIdPerson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public FindByIdPerson findByIdPerson (){
        return new FindByIdPerson();
    }
}
