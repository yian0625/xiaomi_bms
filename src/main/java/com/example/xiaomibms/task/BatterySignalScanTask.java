package com.example.xiaomibms.task;

import com.example.xiaomibms.domain.entity.BatterySignal;
import com.example.xiaomibms.mapper.BatterySignalMapper;
import com.example.xiaomibms.mapper.RuleMapper;
import com.example.xiaomibms.mq.MQProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * 电池信号扫描任务
 * 优化批量处理和并发性能
 */
@Component
@Slf4j
public class BatterySignalScanTask {
    @Autowired
    private BatterySignalMapper batterySignalMapper;

    @Autowired
    private MQProducer MQProducer;

    @Scheduled(cron = "*/5 * * * * ?")
    public void scanSignals() {
        try {
            // 计算当前月的开始和结束时间
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime startOfMonth = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
            LocalDateTime endOfMonth = startOfMonth.plusMonths(1).minusNanos(1);

            // 使用包含所有分片键的查询方法
            List<BatterySignal> signals = batterySignalMapper.findByStatusAndTimeRange(0, startOfMonth, endOfMonth);

            if (signals == null || signals.isEmpty()) {
                log.info("扫描到的新电池信号数量为0");
                return;
            }
            // 处理信号...
        } catch (Exception e) {
            log.error("扫描电池信号任务异常：{}", e.getMessage(), e);
        }
    }
}
