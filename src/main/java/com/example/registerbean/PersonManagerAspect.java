package com.example.registerbean;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class PersonManagerAspect {

    @Before("within(com.example.registerbean.PersonManagerImpl)")
    public void log() {
        log.info("AOP invoke PersonManagerImpl");
    }

    @Before("within(com.example.registerbean.PersonManagerProxy)")
    public void logProxy() {
        log.info("AOP invoke PersonManagerProxy");
    }
}
