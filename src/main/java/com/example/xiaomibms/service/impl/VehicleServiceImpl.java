package com.example.xiaomibms.service.impl;
import com.example.xiaomibms.domain.dto.VehicleDTO;
import com.example.xiaomibms.domain.entity.Vehicle;
import com.example.xiaomibms.mapper.VehicleMapper;
import com.example.xiaomibms.service.VehicleService;
import com.example.xiaomibms.util.VehicleIdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleMapper vehicleMapper;
    @Autowired
    private VehicleIdGenerator vehicleIdGenerator;

    /**
     * 新增车辆信息
     * @param vehicleDTO
     */
    @Override
    public void addVehicle(VehicleDTO vehicleDTO){
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleDTO,vehicle);
        if (vehicle.getVid() == null||vehicle.getVid().isEmpty()) {
            vehicle.setVid(vehicleIdGenerator.createVid());//根据uuid生成16位唯一标识
        }
        vehicleMapper.insert(vehicle);
    }

    /**
     * 根据vid删除车辆信息
     * @param vid
     */
    @Override
    public void deleteVehicleByVid(String vid){
        vehicleMapper.deleteByVid(vid);
    }

    /**
     * 根据id删除车辆信息
     * @param id
     */
    public void deleteVehicleById(Long id){
        vehicleMapper.deleteById(id);
    }

    /**
     * 修改车辆信息
     * @param vehicleDTO
     */
    public void updateVehicle(VehicleDTO vehicleDTO){
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleDTO,vehicle);
        vehicleMapper.update(vehicle);
    }

    /**
     * 根据vid查询车辆信息
     * @param vid
     * @return
     */
    public Vehicle getVehicleByVid(String vid){
        Vehicle vehicle = vehicleMapper.selectByVid(vid);
        return vehicle;
    }

    /**
     * 根据id查询车辆信息
     * @param id
     * @return
     */
    public Vehicle getVehicleById(Long id){
        Vehicle vehicle = vehicleMapper.selectById(id);
        return vehicle;
    }

    /**
     * 根据车架编号查询车辆信息
     * @param frameNumber
     * @return
     */
    public Vehicle getVehicleByFrameNumber(Long frameNumber){
        Vehicle vehicle = vehicleMapper.selectByFrameNumber(frameNumber);
        return vehicle;
    }

    /**
     * 查询所有车辆信息
     * @return
     */
    public List<Vehicle> getAllVehicles(){
        List<Vehicle> vehicles = vehicleMapper.selectAll();
        return vehicles;
    }
}