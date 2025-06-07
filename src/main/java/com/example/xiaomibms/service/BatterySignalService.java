package com.example.xiaomibms.service;

import com.example.xiaomibms.domain.dto.BatterySignalDTO;
import com.example.xiaomibms.domain.entity.BatterySignal;

import java.time.LocalDateTime;
import java.util.List;

public interface BatterySignalService {
        /**
         * 创建电池信号
         * @param batterySignalDTO
         */
        void addSignal(BatterySignalDTO batterySignalDTO);

        /**
         * 根据id删除电池信号
         * @param id
         */
        void deleteSignalById(Long id);


        /**
         * 根据id修改电池信号
         * @param batterySignalDTO
         */
        void updateSignal(BatterySignalDTO batterySignalDTO);

        /**
         * 根据ID获取电池信号
         * @param id
         * @return
         */
        BatterySignal getSignalById(Long id);

        /**
         * 根据carId查询电池信号
         * @param carId
         * @return
         */
        List<BatterySignal> findByCarId(Long carId);

        /**
         * 根据电池类型查询电池信号
         * @param batteryType
         * @return
         */
        List<BatterySignal> findByBatteryType(String batteryType);

        /**
         * 根据信号状态查询电池信号
         * @param status
         * @return
         */
        List<BatterySignal> findByStatus(int status);

        /**
         * 根据时间和状态范围查询电池信号
         * @param status
         * @param startTime
         * @param endTime
         * @return
         */
        List<BatterySignal> findByStatusAndTimeRange(int status, LocalDateTime startTime, LocalDateTime endTime);
}
