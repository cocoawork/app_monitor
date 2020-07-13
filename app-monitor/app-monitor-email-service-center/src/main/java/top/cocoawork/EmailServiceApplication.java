package top.cocoawork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

@EnableJms
@SpringBootApplication
public class EmailServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(EmailServiceApplication.class, args);
    }

}



