# eureka-server

服务注册中心,
启动其他服务都需要配置当前设置的服务中心地址.
eureka.client.serviceUrl.defaultZone=http://localhost:1001/eureka,http://localhost:1002/eureka

当前eureka高可用配置
application-one.properties
application-two.properties
相互注册到服务中心中,保证了高可用


启动方式:
    当前项目打包成jar包, 
启动命令:
    nohup java -jar tt-eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=one
    nohup java -jar tt-eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=two

 