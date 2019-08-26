package com.hy.tt.filter;

import com.netflix.zuul.context.RequestContext;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * @auther thy
 * @date 2019/8/26
 */
public class TrackIdFilter extends  AbstractPreZuulFilter {

    // 声明到common中 提供给所有的项目使用, 测试为了方便就直接写了
    private static final String LOG_TRACK_ID = "traceId";
    private static final String TRACK_ID_HEADER = "trackIdHeader";

    @Override
    public Object doRun() {
        String trackId = String.valueOf(UUID.randomUUID());
        MDC.put(LOG_TRACK_ID,trackId);
        RequestContext currentContext = RequestContext.getCurrentContext();
        currentContext.addZuulRequestHeader(TRACK_ID_HEADER,trackId);
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

}
