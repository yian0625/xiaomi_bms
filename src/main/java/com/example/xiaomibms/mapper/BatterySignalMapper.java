package com.example.xiaomibms.mapper;

import com.example.xiaomibms.domain.entity.BatterySignal;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface BatterySignalMapper {

    /**
     * 新增电池信号
     * @param batterySignal
     */
    @Insert("INSERT INTO battery_signal (car_id, battery_type, max_voltage, min_voltage, max_current, min_current, status, create_time) " +
            "VALUES (#{carId}, #{batteryType}, #{maxVoltage}, #{minVoltage}, #{maxCurrent}, #{minCurrent}, #{status}, #{createTime})")
    void insert(BatterySignal batterySignal);

    /**
     * 根据ID删除电池信号
     * @param id
     */
    @Delete("DELETE from battery_signal where id = #{id}")
    void deleteById(Long id);

    /**
     * 修改电池信号
     * @param batterySignal
     */
    void update(BatterySignal batterySignal);

    /**
     * 根据ID查询电池信号
     * @param id 信号ID
     * @return 电池信号
     */
    @Select("SELECT * FROM battery_signal WHERE id = #{id}")
    BatterySignal findById(Long id);

    /**
     * 根据信号状态查询电池信号
     * @param status
     * @return
     */
    @Select("SELECT * FROM battery_signal WHERE status = #{status}")
    List<BatterySignal> findByStatus(int status);

    /**
     * 根据电池类型查询电池信号
     * @param batteryType
     * @return
     */
    @Select("SELECT * FROM battery_signal WHERE battery_type = #{batteryType}")
    List<BatterySignal> findByBatteryType(String batteryType);

    /**
     * 根据carId查询电池信号
     * @param carId
     * @return
     */
    @Select("SELECT * FROM battery_signal WHERE car_id = #{carId}")
    List<BatterySignal> findByCarId(Long carId);

    /**
     * 根据信号状态和时间范围查询电池信号
     * @param status
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT * FROM battery_signal WHERE status = #{status} AND create_time >= #{startTime} AND create_time <= #{endTime}")
    List<BatterySignal> findByStatusAndTimeRange(int status, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 更新电池信号（不更新分片键）
     * @param batterySignal
     */
    void updateWithoutShardingKey(BatterySignal batterySignal);
}