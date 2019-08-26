package com.hy.tt.filter;

import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther thy
 * @date 2019/8/15
 */
@Slf4j
public class TtTokenZuulFilter extends AbstractPreZuulFilter {
    @Override
    public Object doRun() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

//    @Override
//    public Object doRun() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        String remoteHost = request.getRemoteHost();
//        String method = request.getMethod();
//        String requestURI = request.getRequestURI();
//        Object token = request.getHeader("token");
//        if(token == null){
//            log.info(String.format("host:%s,method:%s,uri:%s token is missing",remoteHost,method,requestURI));
//            return fail(403,String.format("host:%s,method:%s,uri:%s token is missing",remoteHost,method,requestURI));
//        }
//        return success();
//    }
//
//    @Override
//    public int filterOrder() {
//        return 1;
//    }
}
