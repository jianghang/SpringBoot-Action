package com.hangjiang.action.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * Created by jianghang on 2017/6/13.
 */
@Service
@Profile("prod")
public class JmsPtpListener {

    private static Logger logger = LoggerFactory.getLogger(JmsPtpListener.class);

    @JmsListener(destination = "ptp",containerFactory = "jmsQueueListenerCF")
    public void receive(String msg){
        logger.info("ptp design: " + msg);
    }
}
