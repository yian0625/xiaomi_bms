package com.example.xiaomibms.service.impl;

import com.example.xiaomibms.common.RuleItem;
import com.example.xiaomibms.common.SignalData;
import com.example.xiaomibms.domain.dto.WarningDTO;
import com.example.xiaomibms.domain.entity.Rule;
import com.example.xiaomibms.domain.entity.Warning;
import com.example.xiaomibms.mapper.RuleMapper;
import com.example.xiaomibms.mapper.WarningMapper;
import com.example.xiaomibms.service.VehicleService;
import com.example.xiaomibms.service.WarningService;
import com.example.xiaomibms.util.RuleEvaluator;
import com.example.xiaomibms.util.RuleGroupParser;
import com.example.xiaomibms.util.SignalParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WarningServiceImpl implements WarningService {
    @Autowired
    private WarningMapper warningMapper;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private RuleMapper ruleMapper;

    /**
     * 保存预警记录
     * @param warning 预警记录对象
     */
    @Override
    public void saveWarning(Warning warning) {
        warningMapper.insert(warning);
    }

    /**
     * 删除预警记录
     * @param id
     */
    public void deleteWarning(Long id){
        warningMapper.deleteById(id);
    }

    /**
     * 修改预警记录
     * @param warning
     */
    public void updateWarning(Warning warning){
        warningMapper.updateWarning(warning);
    }

    /**
     * 根据车架编号查询预警记录
     * @param carId
     * @return 预警记录列表
     */
    @Override
    public List<Warning> getWarningsByCarId(Long carId) {
        return warningMapper.selectByCarId(carId);
    }

    /**
     * 根据报警请求进行规则匹配生成预警记录
     * @param warningDTOs
     */
    public List<Warning> reportWarning(List<WarningDTO> warningDTOs) {
        List<Warning> warningS = new ArrayList<>();
        for (WarningDTO warningDTO : warningDTOs) {
            //根据车架编号获取电池类型
            String batteryType = vehicleService.getVehicleByFrameNumber(warningDTO.getCarId()).getBatteryType();
            if (batteryType == null) {
                throw new RuntimeException("未查询到车架编号对应的电池类型");
            }
            List<Rule> rules = ruleMapper.selectByRuleNumberAndBatteryType(batteryType, warningDTO.getWarnId());
            SignalData signal = SignalParser.parseSignal(warningDTO.getSignal());
            double diff1=0,diff2=0;
            if (signal.getMi()!=null) {
                diff1 = signal.getMx() - signal.getMi();
            }
            if(signal.getIi()!=null) {
                diff2 = signal.getIx() - signal.getIi();
            }
            if (warningDTO.getWarnId() == null) {
                rules = ruleMapper.selectByBatteryType(batteryType);
            }
            for (Rule rule : rules) {
                try {
                    List<RuleItem> ruleItems = RuleGroupParser.parseRules(rule.getWarningRules());
                    for (RuleItem ruleItem : ruleItems) {
                        if (rule.getRuleNumber() == 1) {
                            if (RuleEvaluator.evaluate(ruleItem, diff1)) {
                                Warning warning = new Warning();
                                warning.setCarId(warningDTO.getCarId());
                                warning.setWarnId(rule.getRuleNumber());
                                warning.setBatteryType(batteryType);
                                warning.setSignalId(0L);
                                if (ruleItem.getLevel() == -1) {
                                    warning.setWarnName("不报警");
                                } else {
                                    warning.setWarnName(rule.getRuleName());
                                }
                                warning.setWarnLevel(ruleItem.getLevel());
                                warningMapper.insert(warning);
                                warningS.add(warning);
                                break;
                            }
                        } else {
                            if (RuleEvaluator.evaluate(ruleItem, diff2)) {
                                Warning warning = new Warning();
                                warning.setCarId(warningDTO.getCarId());
                                warning.setWarnId(rule.getRuleNumber());
                                warning.setBatteryType(batteryType);
                                warning.setSignalId(0L);
                                if (ruleItem.getLevel() == -1) {
                                    warning.setWarnName("不报警");
                                } else {
                                    warning.setWarnName(rule.getRuleName());
                                }
                                warning.setWarnLevel(ruleItem.getLevel());
                                warningMapper.insert(warning);
                                warningS.add(warning);
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return warningS;
    }
}
