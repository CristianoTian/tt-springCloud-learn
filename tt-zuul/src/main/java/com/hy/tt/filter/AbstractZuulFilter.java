package com.hy.tt.filter;

import com.hy.tt.config.ContantValue;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @auther thy
 * @date 2019/8/15
 */
public abstract class AbstractZuulFilter extends ZuulFilter {

    protected RequestContext context;

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return (boolean)ctx.getOrDefault(ContantValue.NEXT_FILTER,true);
    }

    @Override
    public Object run() throws ZuulException {
        context = RequestContext.getCurrentContext();
        return doRun();
    }
    public abstract Object doRun();

    public Object success(){
        context.set(ContantValue.NEXT_FILTER,true);
        return null;
    }

    public Object fail(Integer code, String message){
        context.set(ContantValue.NEXT_FILTER,false);
        context.setSendZuulResponse(false);
        context.getResponse().setContentType("text/html;charset=UTF-8");
        context.setResponseStatusCode(code);
        context.setResponseBody(String.format("{\"result\":\"%s!\"}", message));
        return null;
    }
}
