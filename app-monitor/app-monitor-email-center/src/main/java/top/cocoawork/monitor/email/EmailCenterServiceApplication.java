package top.cocoawork.monitor.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class EmailCenterServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(EmailCenterServiceApplication.class, args);
    }

}



