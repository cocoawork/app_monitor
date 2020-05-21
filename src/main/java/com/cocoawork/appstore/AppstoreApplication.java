package com.cocoawork.appstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@EnableTransactionManagement
@EnableScheduling
@SpringBootApplication
public class AppstoreApplication {

    private static Logger logger = LoggerFactory.getLogger(AppstoreApplication.class);

    public static void main(String[] args) {
        logger.info("应用启动");
        SpringApplication.run(AppstoreApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
