package top.cocoawork.listener;

import org.apache.activemq.Message;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import top.cocoawork.service.EmailService;

import javax.jms.JMSException;

@Component
public class MQListener {

    private Logger logger = LoggerFactory.getLogger(MQListener.class);

    @Autowired
    private EmailService emailService;

    @JmsListener(destination = "${activemq.topic}")
    public void receivedMailMessage(Message message) {
        logger.info("接收到来自消息队列消息==>>>{}", message);
        if (message instanceof ActiveMQMapMessage) {
            ActiveMQMapMessage mapMessage = (ActiveMQMapMessage)message;
            try {
                String to = mapMessage.getString("to");
                String content = mapMessage.getString("content");
                emailService.sendMail(to, "", content);
            } catch (JMSException e) {
                logger.error("消息队列获取数据异常==>>>", e);
            }
        }
    }

}
