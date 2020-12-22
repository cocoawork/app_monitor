package top.cocoawork.monitor.fetcher;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.rocketmq.common.BrokerConfig;
import org.apache.rocketmq.remoting.protocol.RemotingCommand;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import top.cocoawork.monitor.common.constant.ApplicationConstant;
import top.cocoawork.monitor.fetcher.domain.Email;
import top.cocoawork.monitor.fetcher.task.ScheduleFetchDataTask;

@EnableTransactionManagement
@EnableDubbo
@EnableScheduling
@SpringBootApplication
public class AppMonitorDataFetchApplication  {

    public static void main(String[] args) {
        SpringApplication.run(AppMonitorDataFetchApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
