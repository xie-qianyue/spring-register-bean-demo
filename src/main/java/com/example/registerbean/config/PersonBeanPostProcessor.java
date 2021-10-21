package com.example.registerbean.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PersonBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("autoInjectPersonManager")) {
            log.info("Create proxy in postProcessAfterInitialization >>>>>>>>>>>>>>>>");

            // AOP works，因为 personManagerProxy 是通过 @Component 托管给 Spring 容器了
            return applicationContext.getBean("personManagerProxy");

            // AOP doesn't work，因为返回的不是在 spring 容器托管的 bean
            // return new PersonManagerProxy();
        }
        return bean;
    }
}
