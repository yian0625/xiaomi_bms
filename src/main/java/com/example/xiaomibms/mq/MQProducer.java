package com.example.xiaomibms.mq;

import com.example.xiaomibms.domain.entity.BatterySignal;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQProducer {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 将电池信号消息发送到 MQ 中，目标主题为 "batterySignalTopic"
     */
    public void sendBatterySignal(BatterySignal batterySignal) {
        try {
            rocketMQTemplate.convertAndSend("batterySignalTopic", batterySignal);
            log.info("电池信号消息发送成功");
        } catch (Exception e) {
            log.error("电池信号消息发送失败：{}", e.getMessage(), e);
        }
    }
}

