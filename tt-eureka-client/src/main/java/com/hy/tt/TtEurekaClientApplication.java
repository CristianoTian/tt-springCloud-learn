package com.hy.tt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TtEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(TtEurekaClientApplication.class, args);
    }

}
