package com.example.xiaomibms.domain.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("规则实体类")
public class Rule {

    private Long id;

    private Long ruleNumber;

    private String ruleName;

    private String warningRules;

    private String batteryType;
}