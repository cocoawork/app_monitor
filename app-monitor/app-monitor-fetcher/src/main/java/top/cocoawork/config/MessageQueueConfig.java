package top.cocoawork.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Topic;

@Configuration
public class MessageQueueConfig {

    private String brokerUrl;

    @Value("${activemq.topic}")
    private String topicName;

    @Bean
    public Topic activeTopic(){
        return new ActiveMQTopic(topicName);
    }


}
