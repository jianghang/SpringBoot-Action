package com.hangjiang.action.controller;

import com.hangjiang.action.jms.JmsPtpProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jianghang on 2017/6/13.
 */
@RestController
public class JmsPtpController {

    @Autowired
    private JmsPtpProducer jmsPtpProducer;

    @RequestMapping("/convertAndSend")
    public Object converAndSend(){
        jmsPtpProducer.convertAndSend();

        return "success";
    }

}
