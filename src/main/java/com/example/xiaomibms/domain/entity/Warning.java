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
@ApiModel("预警信息实体类")
public class Warning {

    private Long id;

    private Long carId;

    private String batteryType;

    private Long warnId;

    private String warnName;

    private Integer warnLevel;

    private Long signalId;
}