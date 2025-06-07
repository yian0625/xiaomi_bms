package com.example.xiaomibms.controller;

import com.example.xiaomibms.common.Result;
import com.example.xiaomibms.domain.dto.WarningDTO;
import com.example.xiaomibms.domain.entity.Warning;
import com.example.xiaomibms.domain.vo.WarningVO;
import com.example.xiaomibms.service.WarningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
//预警信息controller
@Slf4j
@RestController
@RequestMapping("/Warning")
@Api(tags = "预警信息相关接口")
public class WarningController {
    @Autowired
    private WarningService warningService;

    /**
     * 根据车架编号查询预警信息
     * @param carId 车辆识别编号
     * @return 预警记录列表
     */
    @GetMapping("/{carId}")
    @ApiOperation("根据车架编号查询预警信息")
    public Result<List<Warning>> getWarningsByCarId(@PathVariable Long carId) {
        log.info("根据车架编号查询预警信息:{}", carId);
        List<Warning> warnings = warningService.getWarningsByCarId(carId);
        return Result.success(warnings);
    }

    @PostMapping
    @ApiOperation("上报预警信息")
    public Result<List<WarningVO>> reportWarning(@RequestBody List<WarningDTO> warningDTOs) {
        log.info("上报预警信息:{}", warningDTOs);
            List<Warning> warnings = warningService.reportWarning(warningDTOs);
            List<WarningVO> warningVOS = new ArrayList<>();
            for (Warning warning : warnings) {
                WarningVO warningVO = new WarningVO();
                BeanUtils.copyProperties(warning, warningVO);
                warningVOS.add(warningVO);
            }
            return Result.success(warningVOS);
    }
}
