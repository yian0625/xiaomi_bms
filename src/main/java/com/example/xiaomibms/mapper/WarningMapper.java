package com.example.xiaomibms.mapper;

import com.example.xiaomibms.domain.entity.Warning;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WarningMapper {
    /**
     * 新增警告信息
     * @param warning
     */
    @Insert("INSERT INTO warning(car_id, battery_type, warn_id, warn_name, warn_level, signal_id) " +
            "VALUES(#{carId}, #{batteryType}, #{warnId}, #{warnName}, #{warnLevel}, #{signalId})")
    void insert(Warning warning);

    /**
     * 根据id删除警告信息
     * @param id
     */
    @Delete("DELETE FROM battery_signal WHERE id = #{id}")
    void deleteById(Long id);

    /**
     * 修改警告信息
     * @param warning
     */
    void updateWarning(Warning warning);

    /**
     * 根据车架编号查询警告信息
     * @param carId
     * @return
     */
    @Select("SELECT * FROM warning WHERE car_id = #{carId}")
    List<Warning> selectByCarId(Long carId);

    /**
     * 修改警告信息
     * @param warning
     */
    @Update("UPDATE warning SET car_id = #{car_id},battery_type = #{batteryType}" +
            "warn_id = #{warnId}, warn_name = #{warn_Name},warn_level = #{warnLevel}, signal_id = #{signalId} WHERE id = #{id}")
    void update(Warning warning);
}
