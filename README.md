# tt-springCloud-learn

一、tt-eureka-server
eureka服务搭建,通过不同的启动命令启用不同的端口,便于高可用java -jar x.jar --spring.profiles.active=xxx

二、tt-eureka-client
主要注册到eureka中,提供ip和端口

由于实现注册到注册中心,每次都需要启动注册中心,所以下面的项目暂时删除注册到eureka中.

三、tt-zuul
网关层,路由导向,以及filter处理 tt-ser-one tt-ser-two 辅助测试
链路追踪:
    1.通过网关生成唯一标识,通过filter的addZuulRequestHeader实现放入到header中
    2.通过路由项目Interceptor获取header中的唯一表示放入MDC,并且通过feign的RequestInterceptor设置header
    3.和2类似获取header
        
