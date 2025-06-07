package com.example.xiaomibms.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleDTO {

    private Long id;

    private Long ruleNumber;

    private String ruleName;

    private String warningRules;

    private String batteryType;
}