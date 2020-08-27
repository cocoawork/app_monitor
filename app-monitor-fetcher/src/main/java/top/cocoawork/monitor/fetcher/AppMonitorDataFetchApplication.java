package top.cocoawork.monitor.fetcher;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import top.cocoawork.monitor.fetcher.task.ScheduleFetchDataTask;

@EnableScheduling
@EnableDubbo
@SpringBootApplication
public class AppMonitorDataFetchApplication implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ScheduleFetchDataTask task;


    public static void main(String[] args) {
        SpringApplication.run(AppMonitorDataFetchApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        task.scheduleFetchAppOutline();
        task.scheduleFetchAppInfo();
    }
}
