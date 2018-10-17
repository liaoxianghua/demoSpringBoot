package com.example.demoSpringBoot.controller;

import com.example.demoSpringBoot.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    HelloService helloService;

    @RequestMapping("hello")
    @ResponseBody
    public String hello(HttpServletRequest request,String name){
        logger.info("hello word");
        return helloService.sayHello(name);
    }

    @RequestMapping("upload")
    @ResponseBody
    public String upload(HttpServletRequest request,String name){
        logger.info("upload word");
        return helloService.sayHello(name);
    }

    @RequestMapping("common")
    @ResponseBody
    public String common(HttpServletRequest request,String name){
        logger.info("common word");
        return helloService.sayHello(name);
    }
}
