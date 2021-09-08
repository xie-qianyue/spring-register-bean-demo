package com.example.registerbean;

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
