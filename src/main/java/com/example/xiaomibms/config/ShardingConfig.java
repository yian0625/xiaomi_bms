package com.example.xiaomibms.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.AlgorithmConfiguration;
import org.apache.shardingsphere.infra.config.rule.RuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.ComplexShardingStrategyConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

@Configuration
public class ShardingConfig {

    @Bean
    public DataSource rawDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/xiaomi?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("zjh040625");
        return dataSource;
    }

    @Primary
    @Bean
    public DataSource dataSource(DataSource rawDataSource) throws SQLException {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("ds", rawDataSource);

        // 创建电池信号表分片规则
        ShardingTableRuleConfiguration batterySignalTableRuleConfig = new ShardingTableRuleConfiguration(
                "battery_signal", 
                "ds.battery_signal_${0..1}_${202505..202512}");
                
        // 使用复合分片策略 - 同时按状态和时间分片
        batterySignalTableRuleConfig.setTableShardingStrategy(
                new ComplexShardingStrategyConfiguration("status,create_time", 
                        "batterySignalComplexShardingAlgorithm"));

        // 创建分片规则配置
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTables().add(batterySignalTableRuleConfig);

        // 配置复合分片算法
        Properties complexProps = new Properties();
        shardingRuleConfig.getShardingAlgorithms().put(
                "batterySignalComplexShardingAlgorithm", 
                new AlgorithmConfiguration("CLASS_BASED", complexProps));
        complexProps.setProperty("strategy", "COMPLEX");
        complexProps.setProperty("algorithmClassName", 
                "com.example.xiaomibms.config.BatterySignalComplexShardingAlgorithm");

        Collection<RuleConfiguration> rules = Collections.singleton(shardingRuleConfig);
        Properties props = new Properties();
        props.setProperty("sql-show", "true");
        
        return ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, rules, props);
    }
}