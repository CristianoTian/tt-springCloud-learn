package com.hy.tt.serOne.controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "myServiceI")
@RequestMapping(value = "/one/hello")
public interface IHelloController {

    @RequestMapping(value = "/say")
    public String sayHello();

    @RequestMapping(value = "/get")
    public String getMessage();

}
