package com.example.registerbean.service.impl;

import com.example.registerbean.entity.Person;
import com.example.registerbean.dao.PersonDao;
import com.example.registerbean.service.PersonManager;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component("autoInjectPersonManager")
public class PersonManagerImpl implements PersonManager {

    private PersonDao personDao;

    public Person createPerson() {
        return this.personDao.createPerson();
    }

}
