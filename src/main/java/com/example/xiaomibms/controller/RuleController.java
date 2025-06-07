package com.example.xiaomibms.controller;

import com.example.xiaomibms.common.Result;
import com.example.xiaomibms.domain.dto.RuleDTO;
import com.example.xiaomibms.domain.entity.Rule;
import com.example.xiaomibms.mapper.RuleMapper;
import com.example.xiaomibms.service.RuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//电池规则controller
@Slf4j
@RestController
@RequestMapping("/Rule")
@Api(tags = "规则相关接口")
public class RuleController {
    @Autowired
    private RuleService ruleService;

    /**
     * 新增规则
     * @param ruleDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增规则")
    public Result addRule(@RequestBody RuleDTO ruleDTO) {
        log.info("新增规则:{}", ruleDTO);
        ruleService.addRule(ruleDTO);
        return Result.success();
    }

    /**
     * 根据id删除规则
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除规则")
    public Result deleteRule(@PathVariable Long id) {
        log.info("根据id删除规则:{}", id);
        ruleService.deleteRule(id);
        return Result.success();
    }

    /**
     * 修改规则
     * @param ruleDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改规则")
    public Result updateRule(@RequestBody RuleDTO ruleDTO) {
        log.info("修改规则:{}", ruleDTO);
        ruleService.updateRule(ruleDTO);
        return Result.success();
    }

    /**
     * 根据id查询规则
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询规则")
    public Result<Rule> getRuleById(@PathVariable Long id) {
        log.info("根据id查找规则", id);
        Rule rule = ruleService.getRuleById(id);
        return Result.success(rule);
    }

    /**
     * 根据规则编号查询规则
     * @param ruleNumber
     * @return
     */
    @GetMapping("/ruleNumber")
    @ApiOperation("根据规则编号查询规则")
    public Result<List<Rule>> getRuleByRuleNumber(String ruleNumber) {
        log.info("根据规则编号查询规则", ruleNumber);
        List<Rule> rules = ruleService.getRuleByRuleNumber(ruleNumber);
        return Result.success(rules);
    }

    /**
     * 根据电池类型查询规则
     * @param batteryType
     * @return
     */
    @GetMapping("/batteryType")
    @ApiOperation("根据电池类型查询规则")
    public Result<List<Rule>> getRuleByBatteryType(String batteryType) {
        log.info("根据电池类型查询规则", batteryType);
        List<Rule> rules = ruleService.getRuleBybatteryType(batteryType);
        return Result.success(rules);
    }


    /**
     * 根据规则编号和电池类型查询规则
     * @param batteryType
     * @param ruleNumber
     * @return
     */
    @GetMapping("/batteryTpyeAndRuleNumber")
    @ApiOperation("根据规则编号和电池类型查询规则")
    public Result<List<Rule>> getRuleBybatteryTypeandRuleNumber(String batteryType, Long ruleNumber){
        List<Rule> rules = ruleService.getRuleBybatteryTypeandRuleNumber(batteryType,ruleNumber);
        return Result.success(rules);
    }
}
