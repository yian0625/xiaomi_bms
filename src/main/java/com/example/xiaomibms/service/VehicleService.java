package com.example.xiaomibms.service;

import com.example.xiaomibms.domain.dto.VehicleDTO;
import com.example.xiaomibms.domain.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    /**
     * 新增车辆信息
     * @param vehicleDTO
     */
    void addVehicle(VehicleDTO vehicleDTO);

    /**
     * 根据vid删除车辆信息
     * @param vid
     */
    void deleteVehicleByVid(String vid);

    /**
     * 根据id删除车辆信息
     * @param id
     */
    void deleteVehicleById(Long id);

    /**
     * 修改车辆信息
     * @param vehicleDTO
     */
    void  updateVehicle(VehicleDTO vehicleDTO);

    /**
     * 根据vid查询车辆信息
     * @param vid
     * @return
     */
    Vehicle getVehicleByVid(String vid);

    /**
     * 根据id查询车辆信息
     * @param id
     * @return
     */
    Vehicle getVehicleById(Long id);

    /**
     * 根据车架编号查询车辆信息
     * @param frameNumber
     * @return
     */
    Vehicle getVehicleByFrameNumber(Long frameNumber);

    /**
     * 查询所有车辆信息
     * @return
     */
    List<Vehicle> getAllVehicles();
}