package top.cocoawork;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import top.cocoawork.service.AppDataFetchService;
import top.cocoawork.service.impl.AppDataFetcheServiceImpl;
import top.cocoawork.task.ScheduleTask;

@EnableScheduling
@EnableDubbo
@SpringBootApplication
public class AppMonitorDataFetcheServiceApplication implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ScheduleTask task;


    public static void main(String[] args) {
        SpringApplication.run(AppMonitorDataFetcheServiceApplication.class, args);
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
