package com.example.registerbean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PersonBeanPostFactory implements BeanPostProcessor {

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("autoInjectPersonManager")){
            log.info("Create proxy in postProcessAfterInitialization >>>>>>>>>>>>>>>>");

            // AOP works
            return applicationContext.getBean("personManagerProxy");

            // AOP doesn't work
            // return new PersonManagerProxy();
        }
        return bean;
    }
}
