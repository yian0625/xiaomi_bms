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
public class BatterySignalDTO {

    private Long id;

    private Long carId;

    private String batteryType;

    private Double maxVoltage;

    private Double minVoltage;

    private Double maxCurrent;

    private Double minCurrent;

    private Integer status;

}