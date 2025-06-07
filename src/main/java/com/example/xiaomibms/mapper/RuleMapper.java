package com.example.xiaomibms.mapper;

import com.example.xiaomibms.domain.entity.Rule;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface RuleMapper {
    /**
     * 新增规则
     * @param rule
     */
    @Insert("INSERT INTO rule (rule_number, rule_name, warning_rules, battery_type) " +
            "VALUES (#{ruleNumber}, #{ruleName}, #{warningRules}, #{batteryType})")
    void insert(Rule rule);

    /**
     * 删除规则
     * @param id
     */
    @Delete("DELETE FROM rule WHERE id = #{id}")
    void deleteById(Long id);

    /**
     * 修改规则
     * @param rule
     * @return
     */
    void update(Rule rule);

    /**
     * 根据id查询规则
     * @param id
     * @return
     */
    @Select("SELECT * FROM rule WHERE id = #{id}")
    Rule selectById(Long id);

    /**
     * 通过规则编号查询规则
     * @param ruleNumber
     * @return
     */
    @Select("SELECT * FROM rule WHERE rule_number = #{rulenumber}")
    List<Rule> selectByRuleNo(String ruleNumber);

    /**
     * 通过规则名称和电池类型查询规则
     * @param batteryType
     * @param ruleNumber
     * @return
     */
    @Select("SELECT * FROM rule WHERE battery_type = #{batteryType} AND rule_number = #{ruleNumber}")
    List<Rule> selectByRuleNumberAndBatteryType(String batteryType, Long ruleNumber);

    /**
     * 根据电池类型查询规则
     * @param batteryType
     * @return
     */
    @Select("SELECT * FROM rule WHERE battery_type = #{batteryType}")
    List<Rule>selectByBatteryType(String batteryType);

    /**
     * 查询所有规则
     * @return
     */
    @Select("SELECT * FROM rule")
    List<Rule> findAll();
}
