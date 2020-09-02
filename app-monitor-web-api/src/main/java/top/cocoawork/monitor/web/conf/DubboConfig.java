package top.cocoawork.monitor.web.conf;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.cocoawork.monitor.service.api.UserService;

@Configuration
public class DubboConfig {

    @Reference
    UserService userService;

    @Bean
    public UserService userService() {
        return userService;
    }

}
