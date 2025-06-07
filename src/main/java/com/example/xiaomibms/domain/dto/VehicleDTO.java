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
public class VehicleDTO {

    private Long id;

    private String vid;

    private Long frameNumber;

    private String batteryType;

    private Double totalMileage;

    private Double batteryHealth;

}