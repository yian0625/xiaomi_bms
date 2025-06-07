package com.example.xiaomibms;

import com.example.xiaomibms.controller.RuleController;
import com.example.xiaomibms.domain.dto.RuleDTO;
import com.example.xiaomibms.domain.entity.Rule;
import com.example.xiaomibms.service.RuleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestRule {

    @Autowired
    private RuleController ruleController;

    @Test
    public void add() {
        ruleController.addRule(new RuleDTO(){{
            setRuleNumber(1L);
            setRuleName("电压差报警");
            setBatteryType("三元电池");
            setWarningRules("[{\"level\":0,\"min\":2,\"max\":-1},{\"level\":1,\"min\":1,\"max\":2},{\"level\":2,\"min\":0.7,\"max\":1},{\"level\":3,\"min\":0.4,\"max\":0.7},{\"level\":4,\"min\":0.2,\"max\":0.4},{\"level\":-1,\"min\":-1,\"max\":0.2}]");
        }});
    }

    @Test
    public void delete(){
        ruleController.deleteRule(3L);
    }

    @Test
    public void update(){
        ruleController.updateRule(new RuleDTO(){{
            setId(2L);
            setRuleNumber(2L);
            setRuleName("电流差报警");
            setBatteryType("三元电池");
            setWarningRules("[{\"level\":0,\"min\":2,\"max\":-1},{\"level\":1,\"min\":1,\"max\":2},{\"level\":2,\"min\":0.7,\"max\":1},{\"level\":3,\"min\":0.4,\"max\":0.7},{\"level\":4,\"min\":0.2,\"max\":0.4},{\"level\":-1,\"min\":-1,\"max\":0.2}]");
        }});
    }

    @Test
    public void findById(){
        System.out.println(ruleController.getRuleById(2L));
    }
}
