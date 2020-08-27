package top.cocoawork.monitor;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableDubbo
@EnableTransactionManagement
@SpringBootApplication
public class AppMonitorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppMonitorServiceApplication.class, args);
    }

}
