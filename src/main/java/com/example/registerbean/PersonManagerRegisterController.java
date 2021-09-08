package com.example.registerbean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.registerbean.Constants.*;


@RestController
@Slf4j
public class PersonManagerRegisterController {

    @Autowired
    @Qualifier("autoInjectPersonManager")
    private PersonManager personManager;

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
            case "api":
                // api 注册切面失效
                personManager = applicationContext.getBean(PERSON_MANAGER_API, PersonManagerImpl.class);
                break;
            case "proxy":
                personManager = this.personManager;
                break;
            default:
                throw new RuntimeException(String.format("Manager no support: %s", manager));
        }

        return personManager.createPerson().toString();
    }

    @GetMapping("/person")
    public String person() {
        Person person = (Person) applicationContext.getBean("Person");
        return person.toString();
    }
}
