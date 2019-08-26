package com.hy.tt.config;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther thy
 * @date 2019/8/26
 */
@Component
public class TrackInterceptor implements HandlerInterceptor {

    // 声明到common中 提供给所有的项目使用, 测试为了方便就直接写了
    private static final String LOG_TRACK_ID = "traceId";
    private static final String TRACK_ID_HEADER = "trackIdHeader";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader(TRACK_ID_HEADER);
        if(!StringUtils.isEmpty(handler)){
            MDC.put(LOG_TRACK_ID,header);
        }
        return true;
    }
}
