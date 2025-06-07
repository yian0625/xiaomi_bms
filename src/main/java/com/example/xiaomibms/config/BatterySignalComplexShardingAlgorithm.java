package com.example.xiaomibms.config;

import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.complex.ComplexKeysShardingValue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

public class BatterySignalComplexShardingAlgorithm implements ComplexKeysShardingAlgorithm<Comparable<?>> {
    private Properties props = new Properties();

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
                                         ComplexKeysShardingValue<Comparable<?>> shardingValue) {
        Collection<String> result = new LinkedList<>();
        // 获取分片键值
        Map<String, Collection<Comparable<?>>> columnNameAndShardingValuesMap =
                shardingValue.getColumnNameAndShardingValuesMap();

        // 获取状态值
        Collection<Comparable<?>> statusValues = columnNameAndShardingValuesMap.get("status");
        Integer status = 0; // 默认状态为0
        if (statusValues != null && !statusValues.isEmpty()) {
            status = (Integer) statusValues.iterator().next();
        }

        // 获取创建时间
        Collection<Comparable<?>> createTimeValues = columnNameAndShardingValuesMap.get("create_time");
        String yearMonth = getYearMonthFromDate(createTimeValues);

        // 构建目标表名
        String targetTable = String.format("battery_signal_%d_%s", status, yearMonth);

        // 检查目标表是否在可用表列表中
        for (String availableTargetName : availableTargetNames) {
            if (availableTargetName.endsWith(targetTable)) {
                result.add(availableTargetName);
                break;
            }
        }
        return result;
    }

    private String getYearMonthFromDate(Collection<Comparable<?>> createTimeValues) {
        if (createTimeValues == null || createTimeValues.isEmpty()) {
            // 如果没有指定时间，使用当前时间
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
        }

        Comparable<?> value = createTimeValues.iterator().next();
        if (value instanceof LocalDateTime) {
            return ((LocalDateTime) value).format(DateTimeFormatter.ofPattern("yyyyMM"));
        } else if (value instanceof String) {
            // 尝试将字符串解析为日期
            try {
                return LocalDateTime.parse((String) value)
                        .format(DateTimeFormatter.ofPattern("yyyyMM"));
            } catch (Exception e) {
                return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
            }
        }

        // 默认返回当前年月
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
    }

    @Override
    public void init(Properties props) {
        this.props = props;
    }

    @Override
    public String getType() {
        return "COMPLEX_BATTERY_SIGNAL";
    }

    @Override
    public Properties getProps() {
        return props;
    }
}