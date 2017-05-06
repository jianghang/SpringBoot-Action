package com.hangjiang.action.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jianghang on 2017/3/26.
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String hello(){
        return "hello.html";
    }
}
