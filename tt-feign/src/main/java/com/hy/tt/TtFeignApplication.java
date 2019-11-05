package com.hy.tt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TtFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(TtFeignApplication.class, args);
    }

}
