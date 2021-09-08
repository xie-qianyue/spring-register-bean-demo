package com.example.registerbean;

import org.springframework.stereotype.Component;

@Component
public class PersonManagerProxy implements PersonManager {

    @Override
    public Person createPerson() {
        return new Person("Proxy", 0);
    }
}
