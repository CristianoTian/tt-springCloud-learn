package com.hy.tt.config;

import feign.RequestInterceptor;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @auther thy
 * @date 2019/8/26
 */
@Configuration
public class FeignInterceptorConfig {

    // 声明到common中 提供给所有的项目使用, 测试为了方便就直接写了
    private static final String LOG_TRACK_ID = "traceId";
    private static final String TRACK_ID_HEADER = "trackIdHeader";
    @Bean
    public RequestInterceptor requestInterceptor(){
        RequestInterceptor requestInterceptor = requestTemplate -> {
            String s = MDC.get(LOG_TRACK_ID);
            if(!StringUtils.isEmpty(s)){
                requestTemplate.header(TRACK_ID_HEADER,s);
            }
        };
        return requestInterceptor;
    }
}
