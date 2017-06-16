package com.hangjiang.action.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jianghang on 2017/3/26.
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String hello(){
        return "hello.html";
    }

    @RequestMapping(value = "/helloTymeleaf",method = RequestMethod.GET)
    public String helloThymeleaf(Model model){
        model.addAttribute("name","thymeleaf");

        return "helloTymeleaf";
    }
}
