package com.lantingeee.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lantingeee on 09/11/2017.
 */

@Configuration
public class ApplicationContextUtil implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextUtil.class);

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        logger.info("get applicationContext ");
        return applicationContext;
    }

}
