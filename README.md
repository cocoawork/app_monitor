## APP_MONITOR
从App Store开放的接口中抓取APP数据，存储到数据库，并定时更新；

### 涉及技术
   * SpringBoot
   * MyBatis-Plus
   * Dubbo+Zookeeper
   * ActiveMQ
   * Spring邮件服务
   * JWT+Shiro权限控制
    
### 模块介绍（主要功能）
   * app-monitor-dao：基于Mybatis-Plus的数据存储层
   * app-monitor-service：数据存储业务层
   * app-monitor-service-api：业务接口层
   * app-monitor-fetcher：从AppStore获取数据，调用service业务层存储数据
   * app-monitor-email-service-center：一个简单的邮件发送中心
   
#### 执行流程
   app-monitor-fetcher是一个定时任务，定时从App-Store读取抓据,通过Dubbo远程调用app-monitor-service层进行数据保存，
   数据抓取完毕后向app-monitor-email-service-center消息队列发送消息，消息队列emial服务监听到消息会发送邮件提醒任务完成。
    
    