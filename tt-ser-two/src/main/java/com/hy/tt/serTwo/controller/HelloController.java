package com.hy.tt.serTwo.controller;

import com.hy.tt.serOne.controller.IHelloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @auther thy
 * @date 2019/8/15
 */
@RestController
@RequestMapping("/two/hello")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private IHelloController ihelloController;

    @RequestMapping(value = "/say")
    public String sayHello(){
        logger.info("test trackId  begin");
        System.out.println("|||||||||||||||||||||||||||||||");
        logger.info("test trackId  end");
        String message = ihelloController.getMessage();
        return message + " | hello zuul, I am myServiceII two";
    }
}
