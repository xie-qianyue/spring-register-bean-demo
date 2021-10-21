package com.example.registerbean;

import com.example.registerbean.service.PersonManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.registerbean.util.Constants.PERSON_MANAGER_BEAN_DEF;
import static com.example.registerbean.util.Constants.PERSON_MANAGER_SINGLETON;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterBeanApplicationTests {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testRegisterBeanInBeanFactoryPostProcessor() {
        PersonManager personManagerBeanDef = applicationContext.getBean(PERSON_MANAGER_BEAN_DEF, PersonManager.class);
        assertNotNull(personManagerBeanDef);

        PersonManager personManagerSingleton = applicationContext.getBean(PERSON_MANAGER_SINGLETON, PersonManager.class);
        assertNotNull(personManagerSingleton);

        assertFalse(personManagerBeanDef == personManagerSingleton);
    }

}
