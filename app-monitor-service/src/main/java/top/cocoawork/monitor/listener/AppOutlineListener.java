package top.cocoawork.monitor.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.cocoawork.monitor.common.constant.ApplicationConstant;
import top.cocoawork.monitor.dao.entity.AppOutline;
import top.cocoawork.monitor.service.api.AppOutlineService;
import top.cocoawork.monitor.service.api.dto.AppOutlineDto;


@RocketMQMessageListener(consumerGroup = "app-monitor-appoutline-consumer-group",
        topic = ApplicationConstant.MQ_TOPIC,
        selectorExpression = ApplicationConstant.MQ_TOPIC_TAG_APPOUTLINE)
@Component
public class AppOutlineListener implements RocketMQListener<AppOutlineDto> {

    @Autowired
    private AppOutlineService appOutlineService;

    @Override
    public void onMessage(AppOutlineDto message) {
        appOutlineService.insert(message);
    }
}
