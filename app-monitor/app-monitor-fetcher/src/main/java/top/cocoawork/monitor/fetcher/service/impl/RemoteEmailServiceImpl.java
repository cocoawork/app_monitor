package top.cocoawork.monitor.fetcher.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;
import top.cocoawork.monitor.fetcher.service.RemoteEmailService;

import javax.jms.Topic;
import java.util.HashMap;
import java.util.Map;

@Service
public class RemoteEmailServiceImpl implements RemoteEmailService {

    private Logger logger = LoggerFactory.getLogger(RemoteEmailServiceImpl.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Topic topic;

    @Override
    public void sendEmail(String to, String content) {
        Map<String, Object> map = new HashMap();
        map.put("to", to);
        map.put("content", content);
        try {
            jmsMessagingTemplate.convertAndSend(topic, map);
        }catch (MessagingException e) {
            logger.error("JmsMessagingTemplate消息队列发送消息错误",e);
        }
    }
}
