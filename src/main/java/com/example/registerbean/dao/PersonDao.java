package com.example.registerbean.dao;


import com.example.registerbean.entity.Person;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class PersonDao {

    private static final Random RANDOM = new Random();

    public Person createPerson() {
        String name = UUID.randomUUID().toString();
        int age = RANDOM.nextInt(100);
        return new Person(name,age);
    }
}
