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
@ApiModel("车辆信息实体类")
public class Vehicle {

    private Long id;

    private String vid;

    private Long frameNumber;

    private String batteryType;

    private Double totalMileage;

    private Double batteryHealth;

}