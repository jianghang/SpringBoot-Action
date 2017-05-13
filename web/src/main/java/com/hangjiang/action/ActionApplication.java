package com.hangjiang.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

/**
 * Created by jianghang on 2017/3/26.
 */
@SpringBootApplication
public class ActionApplication extends SpringBootServletInitializer{

    private static Logger logger = LoggerFactory.getLogger(ActionApplication.class);

    public static void main(String[] args){
        ApplicationContext applicationContext = SpringApplication.run(ActionApplication.class,args);
        String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
        for(String profile : activeProfiles){
            logger.info("Srping Boot profile:{}",profile);
        }
    }
}
