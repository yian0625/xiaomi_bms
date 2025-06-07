package com.example.xiaomibms.mq;

import com.example.xiaomibms.common.RuleItem;
import com.example.xiaomibms.domain.entity.BatterySignal;
import com.example.xiaomibms.domain.entity.Rule;
import com.example.xiaomibms.domain.entity.Vehicle;
import com.example.xiaomibms.domain.entity.Warning;
import com.example.xiaomibms.mapper.BatterySignalMapper;
import com.example.xiaomibms.mapper.RuleMapper;
import com.example.xiaomibms.mapper.VehicleMapper;
import com.example.xiaomibms.mapper.WarningMapper;
import com.example.xiaomibms.util.RuleEvaluator;
import com.example.xiaomibms.util.RuleGroupParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RocketMQMessageListener(topic = "batterySignalTopic", consumerGroup = "batterySignalConsumerGroup")
public class MQConsumer implements RocketMQListener<BatterySignal> {
    @Autowired
    private RuleMapper ruleMapper;

    @Autowired
    private BatterySignalMapper batterySignalMapper;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private WarningMapper warningMapper;

    @Override
    public void onMessage(BatterySignal batterySignal) {
        log.info("接收到电池信号消息：{}", batterySignal);
        List<Rule> rules = ruleMapper.selectByBatteryType(batterySignal.getBatteryType());
        if (rules == null || rules.isEmpty()) {
            log.info("未查询到电池类型 {} 对应的规则",batterySignal.getBatteryType());
            return;
        }
        for (Rule rule : rules) {
            try {
                String jsonRules = rule.getWarningRules();
                List<RuleItem> ruleItems;
                try {
                    ruleItems = RuleGroupParser.parseRules(jsonRules);
                } catch (Exception e) {
                    log.error("Error parsing rule group JSON: {}", e.getMessage(), e);
                    return;
                }
                double diff;
                if(rule.getRuleNumber()==1) {
                    diff = batterySignal.getMaxVoltage() - batterySignal.getMinVoltage();
                }else{
                    diff = batterySignal.getMaxCurrent() - batterySignal.getMinCurrent();
                }
                for (RuleItem ruleItem : ruleItems) {
                    if (RuleEvaluator.evaluate(ruleItem, diff)) {
                        Warning warning = new Warning();
                        warning.setCarId(batterySignal.getCarId());
                        warning.setBatteryType(batterySignal.getBatteryType());
                        warning.setWarnId(rule.getRuleNumber());
                        if(ruleItem.getLevel() == -1){
                            warning.setWarnName("不报警");
                        }else{
                            warning.setWarnName(rule.getRuleName());
                        }
                        warning.setWarnLevel(ruleItem.getLevel());
                        warning.setSignalId(batterySignal.getId());
                        warningMapper.insert(warning);
                        log.info("Generated warning: carId={}, matched rule: {}, warnLevel={}",
                                warning.getCarId(), rule.getRuleName(), ruleItem.getLevel());
                        break;
                    }
                }
                BatterySignal originalSignal = batterySignalMapper.findById(batterySignal.getId());
                if (originalSignal != null) {
                    // 删除原记录
                    batterySignalMapper.deleteById(originalSignal.getId());

                    // 设置新状态
                    batterySignal.setStatus(1);
                    // 保持原始创建时间
                    batterySignal.setCreateTime(originalSignal.getCreateTime());

                    //  插入新记录
                    batterySignalMapper.insert(batterySignal);

                    log.info("电池信号状态已更新为已处理：id={}", batterySignal.getId());
                } else {
                    log.warn("未找到要处理的电池信号：id={}", batterySignal.getId());
                }
        }catch (Exception e) {
            log.error(e.getMessage(), e);}
        }
    }
}
