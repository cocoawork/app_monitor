package top.cocoawork.monitor.email.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import top.cocoawork.monitor.email.service.EmailCenterService;

@Service
public class EmailCenterServiceImpl implements EmailCenterService {

    private Logger logger = LoggerFactory.getLogger(EmailCenterServiceImpl.class);

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Override
    public void sendMail(String reveiver, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(reveiver);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(mailFrom);
        try {
            mailSender.send(message);
        }catch (MailException e) {
            logger.error("调用JavaMailSender发送邮件失败", e);
        }
    }
}
