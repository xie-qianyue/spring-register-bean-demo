package com.example.registerbean.config;

import com.example.registerbean.dao.PersonDao;
import com.example.registerbean.service.impl.PersonManagerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

import static com.example.registerbean.util.Constants.PERSON_MANAGER_BEAN_DEF;
import static com.example.registerbean.util.Constants.PERSON_MANAGER_SINGLETON;


@Component
@Slf4j
public class PersonBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;

        log.info("Register personManager by bean definition >>>>>>>>>>>>>>>>");
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(PersonManagerImpl.class);
        beanDefinitionBuilder.addPropertyReference("personDao", "personDao");
        BeanDefinition personManagerBeanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        defaultListableBeanFactory.registerBeanDefinition(PERSON_MANAGER_BEAN_DEF, personManagerBeanDefinition);

        log.info("Register personManager in singleton way >>>>>>>>>>>>>>>>");
        PersonDao personDao = beanFactory.getBean(PersonDao.class);
        PersonManagerImpl personManager = new PersonManagerImpl();
        personManager.setPersonDao(personDao);
        beanFactory.registerSingleton(PERSON_MANAGER_SINGLETON, personManager);

        log.info("Register person by bean definition >>>>>>>>>>>>>>>>");
        BeanDefinition beanDefinition = defaultListableBeanFactory.getBeanDefinition("Person");
        MutablePropertyValues pv = beanDefinition.getPropertyValues();
        pv.add("name", "Micheal");
    }
}
