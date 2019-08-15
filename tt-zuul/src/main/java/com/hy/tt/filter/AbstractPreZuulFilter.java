package com.hy.tt.filter;


import com.hy.tt.config.FilterType;

/**
 * @auther thy
 * @date 2019/8/15
 */
public abstract class AbstractPreZuulFilter extends AbstractZuulFilter {
    @Override
    public String filterType() {
        return FilterType.PRE;
    }
}
