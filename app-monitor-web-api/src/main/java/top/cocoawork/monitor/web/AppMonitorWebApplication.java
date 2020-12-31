package top.cocoawork.monitor.web;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.util.Timer;

@EnableDubbo
@SpringBootApplication
public class AppMonitorWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppMonitorWebApplication.class, args);
    }


}
