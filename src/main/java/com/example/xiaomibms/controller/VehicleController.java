package com.example.xiaomibms.controller;

import com.example.xiaomibms.common.Result;
import com.example.xiaomibms.domain.dto.VehicleDTO;
import com.example.xiaomibms.domain.entity.Vehicle;
import com.example.xiaomibms.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//车辆信息controller
@Slf4j
@RestController
@RequestMapping("/Vehicle")
@Api(tags = "车辆信息相关接口")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    /**
     * 新增车辆信息
     * @param vehicleDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增车辆信息")
    public Result addVehicle(@RequestBody VehicleDTO vehicleDTO) {
        log.info("新增车辆信息:{}", vehicleDTO);
        vehicleService.addVehicle(vehicleDTO);
        return Result.success();
    }

    /**
     * 修改车辆信息
     * @param vehicleDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改车辆信息")
    public Result updateVehicle(@RequestBody VehicleDTO vehicleDTO) {
        log.info("修改车辆信息:{}", vehicleDTO);
        vehicleService.updateVehicle(vehicleDTO);
        return Result.success();
    }

    /**
     * 根据车辆标识码删除车辆信息
     * @param vid
     * @return
     */
    @DeleteMapping
    @ApiOperation("根据vid删除车辆信息")
    public Result deleteVehicle(@RequestParam  String vid) {
        log.info("根据vid删除车辆信息:{}", vid);
        vehicleService.deleteVehicleByVid(vid);
        return Result.success();
    }

    /**
     * 根据id删除车辆信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除车辆信息")
    public Result deleteVehicle(@PathVariable Long id) {
        log.info("根据id删除车辆信息:{}", id);
        vehicleService.deleteVehicleById(id);
        return Result.success();
    }

    /**
     * 根据vid查找车辆信息
     * @param vid
     * @return
     */
    @GetMapping
    @ApiOperation("根据vid查找车辆信息")
    public Result<Vehicle> getVehicle(@RequestParam  String vid) {
        log.info("根据vid查找车辆信息", vid);
        Vehicle vehicle = vehicleService.getVehicleByVid(vid);
        return Result.success(vehicle);
    }
}
