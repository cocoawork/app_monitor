package top.cocoawork.monitor.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.cocoawork.monitor.common.constant.ApplicationConstant;
import top.cocoawork.monitor.service.api.AppInfoService;
import top.cocoawork.monitor.service.api.dto.AppInfoDto;

@RocketMQMessageListener(topic = ApplicationConstant.MQ_TOPIC,
                            consumerGroup = "app-monitor-appinfo-consumer-group",
                            selectorExpression = ApplicationConstant.MQ_TOPIC_TAG_APPINFO)
@Component
public class AppInfoListener implements RocketMQListener<AppInfoDto> {

    @Autowired
    private AppInfoService appInfoService;

    @Override
    public void onMessage(AppInfoDto message) {
        appInfoService.insert(message);
    }
}
