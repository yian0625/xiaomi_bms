package com.example.xiaomibms.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarningVO {

    private Long carId;

    private String batteryType;

    private String warnName;

    private Integer warnLevel;
}