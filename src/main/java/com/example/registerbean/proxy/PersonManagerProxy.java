package com.example.registerbean.proxy;

import com.example.registerbean.entity.Person;
import com.example.registerbean.service.PersonManager;
import org.springframework.stereotype.Component;

@Component
public class PersonManagerProxy implements PersonManager {

    @Override
    public Person createPerson() {
        return new Person("Proxy", 0);
    }
}
