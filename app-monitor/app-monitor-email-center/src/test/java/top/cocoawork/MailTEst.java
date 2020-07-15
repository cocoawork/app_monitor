package top.cocoawork;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.cocoawork.service.EmailCenterService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MailTEst {

    @Autowired
    private EmailCenterService emailService;

    @Test
    public void contextLoad(){}


    @Test
    public void testMail() {
        emailService.sendMail("296832852@qq.com", "subject", "texgggggg");
    }
}
