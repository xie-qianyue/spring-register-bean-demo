package com.example.registerbean.controller;

import com.example.registerbean.entity.Person;
import com.example.registerbean.dao.PersonDao;
import com.example.registerbean.service.NotAProxyManager;
import com.example.registerbean.service.PersonManager;
import com.example.registerbean.service.impl.PersonManagerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.registerbean.util.Constants.*;


@RestController
@Slf4j
public class PersonManagerRegisterController {

    @Autowired
    @Qualifier("autoInjectPersonManager")
    private PersonManager personManager;

    @Autowired
    private NotAProxyManager notAProxyManager;

    @Autowired
    GenericApplicationContext applicationContext;

    @Autowired
    ConfigurableBeanFactory beanFactory;

    @PostMapping("/registerPersonManager")
    public void registerPersonManager() {
        PersonDao personDao = applicationContext.getBean(PersonDao.class);
        PersonManagerImpl personManager = new PersonManagerImpl();
        personManager.setPersonDao(personDao);
        beanFactory.registerSingleton(PERSON_MANAGER_API, personManager);
    }

    @GetMapping("/person/{manager}")
    public String person(@PathVariable("manager") String manager) {
        PersonManager personManager;
        switch (manager) {
            case "beanDef":
                personManager = applicationContext.getBean(PERSON_MANAGER_BEAN_DEF, PersonManager.class);
                break;
            case "singleton":
                personManager = applicationContext.getBean(PERSON_MANAGER_SINGLETON, PersonManager.class);
                break;
            case "proxy":
                personManager = this.personManager;
                break;
            case "api":
                // 只有通过 registerSingleton 注册的 bean 不是 proxy，所以切面也会失效
                personManager = applicationContext.getBean(PERSON_MANAGER_API, PersonManagerImpl.class);
                break;
            default:
                throw new RuntimeException(String.format("Manager no support: %s", manager));
        }

        log.info("Is personManager a proxy? " + personManager.getClass().getName());

        return personManager.createPerson().toString();
    }

    @GetMapping("/person")
    public String person() {
        Person person = (Person) applicationContext.getBean("Person");
        return person.toString();
    }

    @GetMapping("/noproxy")
    public String noproxy(){
        String className = notAProxyManager.getClass().getName();
        log.info("I'm not a proxy: " + className);
        return className;
    }
}
