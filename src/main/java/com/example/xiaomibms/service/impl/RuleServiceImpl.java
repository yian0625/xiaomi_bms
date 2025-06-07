package com.example.xiaomibms.service.impl;

import com.example.xiaomibms.domain.dto.RuleDTO;
import com.example.xiaomibms.domain.entity.Rule;
import com.example.xiaomibms.mapper.RuleMapper;
import com.example.xiaomibms.service.RuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RuleServiceImpl implements RuleService {
    @Autowired
    private RuleMapper ruleMapper;

    /**
     * 新增规则
     * @param ruleDTO
     */
    public void addRule(RuleDTO ruleDTO){
        Rule rule = new Rule();
        BeanUtils.copyProperties(ruleDTO,rule);
        ruleMapper.insert(rule);
    }

    /**
     * 删除规则
     * @param id
     */
    public void deleteRule(Long id){
        ruleMapper.deleteById(id);
    }

    /**
     * 修改规则
     * @param ruleDTO
     */
    public void updateRule(RuleDTO ruleDTO){
        Rule rule = new Rule();
        BeanUtils.copyProperties(ruleDTO,rule);
        ruleMapper.update(rule);
    }

    /**
     * 根据id查询规则
     * @param id
     * @return
     */
    public Rule getRuleById(Long id){
        Rule rule = ruleMapper.selectById(id);
        return rule;
    }

    /**
     * 根据规则编号查询规则
     * @param ruleNumber
     * @return
     */
    public List<Rule> getRuleByRuleNumber(String ruleNumber){
        List<Rule> rules = ruleMapper.selectByRuleNo(ruleNumber);
        return rules;
    }

    /**
     * 根据电池类型查询规则
     * @param batteryType
     * @return
     */
    public List<Rule> getRuleBybatteryType(String batteryType){
        List<Rule> rules = ruleMapper.selectByBatteryType(batteryType);
        return rules;
    }

    /**
     * 根据规则编号和电池类型查询规则
     * @param batteryType
     * @param ruleNumber
     * @return
     */
    public List<Rule> getRuleBybatteryTypeandRuleNumber(String batteryType, Long ruleNumber){
        List<Rule> rules = ruleMapper.selectByRuleNumberAndBatteryType(batteryType,ruleNumber);
        return rules;
    }

    /**
     * 查询所有规则
     * @return
     */
    public List<Rule> getAllRules(){
        List<Rule> rules = ruleMapper.findAll();
        return rules;
    }
}