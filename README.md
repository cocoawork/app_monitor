## APP_MONITOR
AppStore开放的接口抓取数据

### 涉及技术
   * SpringBoot
   * MyBatis-Plus
   * Dubbo+Zookeeper/Nacos注册中心
   * RocketMQ消息队列
   * Redis数据缓存
   * Spring邮件服务
   * JWT+Shiro权限控制
    
### 模块介绍（主要功能）
   * app-monitor-dao：基于Mybatis-Plus的数据持久层
   * app-monitor-service：数据业务层
   * app-monitor-service-api：业务接口层
   * app-monitor-fetcher：从AppStore获取数据，存入RocketMQ消息队列
   * app-monitor-email-center：一个简单的邮件发送中心
   
### 执行流程
   app-monitor-fetcher是一个定时任务，定时从App-Store读取抓据,将数据存入RocketMQ，Service层作为消费者监听RocketMQ消息，调用持久层将数据持久化；
   数据抓取完毕后向RocketMQ消息队列发送email消息，消息队列emial服务监听到消息会发送邮件提醒任务完成。
    
    