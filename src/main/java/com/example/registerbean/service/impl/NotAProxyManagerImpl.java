package com.example.registerbean.service.impl;

import com.example.registerbean.entity.Person;
import com.example.registerbean.service.NotAProxyManager;
import org.springframework.stereotype.Component;

@Component
public class NotAProxyManagerImpl implements NotAProxyManager {

    @Override
    public Person createPerson() {
        return new Person("Not a proxy", 1);
    }
}
