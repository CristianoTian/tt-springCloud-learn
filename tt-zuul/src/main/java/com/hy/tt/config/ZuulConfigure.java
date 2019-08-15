package com.hy.tt.config;

import com.hy.tt.filter.RateLimiterFilter;
import com.hy.tt.filter.TtTokenZuulFilter;
import com.netflix.zuul.ZuulFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @auther thy
 * @date 2019/8/15
 */
@Component
public class ZuulConfigure {

    @Bean
    public ZuulFilter tokenFilter(){
        return new TtTokenZuulFilter();
    }

    @Bean
    public ZuulFilter rateLimiterFile(){
        return new RateLimiterFilter();
    }

}
