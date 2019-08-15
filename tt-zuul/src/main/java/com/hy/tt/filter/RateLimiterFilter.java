package com.hy.tt.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther thy
 * @date 2019/8/15
 */
@Slf4j
public class RateLimiterFilter extends AbstractPreZuulFilter {

    RateLimiter rateLimiter = RateLimiter.create(50);

    @Override
    public Object doRun() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String url = request.getRequestURI();
        if(rateLimiter.tryAcquire()){
            return success();
        }else{
            log.info(String.format("rate limit %s",url));
            return fail(401,String.format("rate limit{}",url));
        }
    }

    @Override
    public int filterOrder() {
        return 0;
    }
}
