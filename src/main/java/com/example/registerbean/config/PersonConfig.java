package com.example.registerbean.config;

import com.example.registerbean.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class PersonConfig {

    @Bean
    public Person Person(){
        log.info("Initialize person >>>>>>>>>>>>>>>>");
        return new Person("Jackson", 39);
    }
}
