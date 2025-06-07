package com.example.xiaomibms.service;

import com.example.xiaomibms.domain.dto.RuleDTO;
import com.example.xiaomibms.domain.entity.Rule;

import java.util.List;

public interface RuleService {
    /**
     * 新增规则
     * @param ruleDTO
     */
    void addRule(RuleDTO ruleDTO);

    /**
     * 删除规则
     * @param id
     */
    void deleteRule(Long id);

    /**
     * 修改规则
     * @param ruleDTO
     */
    void updateRule(RuleDTO ruleDTO);

    /**
     * 根据id查询规则
     * @param id
     * @return
     */
    Rule getRuleById(Long id);

    /**
     * 根据规则编号查询规则
     * @param ruleNumber
     * @return
     */
    List<Rule> getRuleByRuleNumber(String ruleNumber);

    /**
     * 根据电池类型查询规则
     * @param batteryType
     * @return
     */
    List<Rule> getRuleBybatteryType(String batteryType);

    /**
     * 根据规则编号和电池类型查询规则
     * @param batteryType
     * @param ruleNumber
     * @return
     */
    List<Rule> getRuleBybatteryTypeandRuleNumber(String batteryType, Long ruleNumber);


    /**
     * 查询所有规则
     * @return
     */
    List<Rule> getAllRules();
}