package com.hangjiang.action.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by jianghang on 2017/6/13.
 */
@Component
public class JmsPtpProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void convertAndSend(){
        jmsTemplate.convertAndSend("ptp","hello,this is convertandsend!");
    }
}
