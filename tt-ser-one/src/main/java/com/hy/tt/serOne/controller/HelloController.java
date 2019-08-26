package com.hy.tt.serOne.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther thy
 * @date 2019/8/15
 */
@RestController
@RequestMapping("/one/hello")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = "/say")
    public String sayHello(){
        return "hello zuul, I am myServiceI";
    }

    @RequestMapping(value = "/get")
    public String getMessage(){
        logger.info("test feign  begin");
        System.out.println("|||||||||||||||||||||||||||||||");
        logger.info("test feign  end");
        return "hello feign, Im one";
    }
}
