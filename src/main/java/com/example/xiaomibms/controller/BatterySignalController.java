package com.example.xiaomibms.controller;

import com.example.xiaomibms.common.Result;
import com.example.xiaomibms.domain.dto.BatterySignalDTO;
import com.example.xiaomibms.domain.entity.BatterySignal;
import com.example.xiaomibms.service.BatterySignalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

//电池信号controller
@Slf4j
@RestController
@RequestMapping("/BatterySignal")
@Api(tags = "电池信号相关接口")
public class BatterySignalController {
    @Autowired
    private BatterySignalService batterySignalService;

    /**
     * 新增电池信号
     * @param batterySignalDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增电池信号")
    public Result addSignal(@RequestBody BatterySignalDTO batterySignalDTO){
        log.info("新增电池信号:{}", batterySignalDTO);
        batterySignalService.addSignal(batterySignalDTO);
        return Result.success();
    }

    /**
     * 根据id删除电池信号
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除电池信号")
    public Result deleteSignal(@PathVariable Long id) {
        log.info("根据id删除电池信号:{}", id);
        batterySignalService.deleteSignalById(id);
        return Result.success();
    }

    /**
     * 修改电池信号
     * @param batterySignalDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改电池信号")
    public Result updateSignal(@RequestBody BatterySignalDTO batterySignalDTO) {
        log.info("修改电池信号:{}", batterySignalDTO);
        batterySignalService.updateSignal(batterySignalDTO);
        return Result.success();
    }

    /**
     * 根据id查询电池信号
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询电池信号")
    public Result<BatterySignal> getSignalById(@PathVariable Long id) {
        log.info("根据id查询电池信号", id);
        BatterySignal batterySignal = batterySignalService.getSignalById(id);
        return Result.success(batterySignal);
    }

    /**
     * 根据carId查询电池信号
     * @param carId
     * @return
     */
    @GetMapping("/carId")
    @ApiOperation("根据carId查询电池信号")
    public Result<List<BatterySignal>> getSignalByVid(Long carId) {
        log.info("根据carId查询电池信号:{}", carId);
        List<BatterySignal> batterySignalList = batterySignalService.findByCarId(carId);
        return Result.success(batterySignalList);
    }

    /**
     * 根据电池类型查询电池信号
     * @param batteryType
     * @return
     */
    @GetMapping("/batteryType")
    @ApiOperation("根据电池类型查询电池信号")
    public Result<List<BatterySignal>> getSignalByBatteryType(@RequestBody String batteryType) {
        log.info("根据电池类型查询电池信号:{}", batteryType);
        List<BatterySignal> batterySignalList = batterySignalService.findByBatteryType(batteryType);
        return Result.success(batterySignalList);
    }

    /**
     * 根据信号状态查询电池信号
     * @param status
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("根据信号状态查询电池信号")
    public Result<List<BatterySignal>> getSignalById(@RequestBody int status) {
        log.info("根据信号状态查询电池信号:{}", status);
        List<BatterySignal> batterySignalList = batterySignalService.findByStatus(status);
        return Result.success(batterySignalList);
    }

    @GetMapping("/status/{status}/time-range")
    @ApiOperation("根据信号状态和时间范围查询电池信号")
    public Result<List<BatterySignal>> getSignalByStatusAndTimeRange(
            @PathVariable int status,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        log.info("根据信号状态和时间范围查询电池信号: status={}, startTime={}, endTime={}",
                status, startTime, endTime);
        List<BatterySignal> batterySignalList =
                batterySignalService.findByStatusAndTimeRange(status, startTime, endTime);
        return Result.success(batterySignalList);
    }
}
