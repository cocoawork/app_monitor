package top.cocoawork.monitor.email.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.cocoawork.monitor.common.constant.ApplicationConstant;
import top.cocoawork.monitor.email.domain.Email;
import top.cocoawork.monitor.email.service.EmailService;


@RocketMQMessageListener(topic = ApplicationConstant.MQ_TOPIC, consumerGroup = "app-monitor-email-consumer-group", selectorExpression = ApplicationConstant.MQ_TOPIC_TAG_EMAIL)
@Component
public class EmailListener implements RocketMQListener<Email> {

    private Logger logger = LoggerFactory.getLogger(EmailListener.class);

    @Autowired
    private EmailService emailService;

    @Override
    public void onMessage(Email message) {
        emailService.sendMail(message.getTo(), message.getSubject(), message.getContent());
    }
}
