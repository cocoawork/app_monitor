package top.cocoawork.monitor.service;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import top.cocoawork.monitor.dao.conf.TableFieldAutoFillHandler;

@EnableDubbo
@Import(value = {TableFieldAutoFillHandler.class})
@MapperScan("top.cocoawork.dao.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class AppMonitorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppMonitorServiceApplication.class, args);
    }

}
