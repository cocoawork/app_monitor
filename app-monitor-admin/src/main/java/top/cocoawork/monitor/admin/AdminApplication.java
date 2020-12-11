package top.cocoawork.monitor.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(AdminApplication.class,args);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
