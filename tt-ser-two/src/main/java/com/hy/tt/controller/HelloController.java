package com.hy.tt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther thy
 * @date 2019/8/15
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(value = "/say")
    public String sayHello(){
        return "hello zuul, I am myServiceII two";
    }
}
