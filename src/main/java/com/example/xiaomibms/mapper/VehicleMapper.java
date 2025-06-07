package com.example.xiaomibms.mapper;


import com.example.xiaomibms.domain.entity.Vehicle;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VehicleMapper {

    /**
     * 新增车辆信息
     * @param vehicle
     */
    @Insert("INSERT INTO vehicle (vid, frame_number, battery_type, total_mileage, battery_health) " +
            "VALUES (#{vid}, #{frameNumber}, #{batteryType}, #{totalMileage}, #{batteryHealth})")
    void insert(Vehicle vehicle);

    /**
     * 根据vid删除车辆信息
     * @param vid
     */
    @Delete("DELETE FROM vehicle WHERE vid = #{vid}")
    void deleteByVid(String vid);

    /**
     * 根据id删除车辆信息
     * @param id
     */
    @Delete("DELETE FROM vehicle WHERE id = #{id}")
    void deleteById(Long id);

    /**
     * 修改车辆信息
     * @param vehicle
     */
     void update(Vehicle vehicle);

    /**
     * 通过id查询车辆信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM vehicle WHERE id = #{id}")
    Vehicle selectById(Long id);


    /**
     * 通过VID查询车辆信息
     * @param vid
     * @return
     */
    @Select("SELECT * FROM vehicle WHERE vid = #{vid}")
    Vehicle selectByVid(String vid);

    /**
     * 通过车架号查询车辆信息
     * @param frameNumber
     * @return
     */
    @Select("SELECT * FROM vehicle WHERE frame_number = #{frameNumber}")
    Vehicle selectByFrameNumber(Long frameNumber);

    /**
     * 查询所有车辆信息
     * @return
     */
    @Select("SELECT * FROM vehicle")
    List<Vehicle> selectAll();
}