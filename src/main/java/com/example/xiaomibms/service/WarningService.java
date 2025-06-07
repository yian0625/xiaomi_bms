package com.example.xiaomibms.service;

import com.example.xiaomibms.domain.dto.WarningDTO;
import com.example.xiaomibms.domain.entity.Warning;
import com.example.xiaomibms.domain.vo.WarningVO;

import java.util.List;

public interface WarningService {
    /**
     * 保存预警记录
     * @param warning 预警记录对象
     */
    void saveWarning(Warning warning);

    /**
     * 删除预警记录
     * @param id
     */
    void deleteWarning(Long id);

    /**
     * 修改预警记录
     * @param warning
     */
    void updateWarning(Warning warning);

    /**
     * 根据车架编号查询预警记录
     * @param carId
     * @return 预警记录列表
     */
    List<Warning> getWarningsByCarId(Long carId);
    /**
     * 根据报警请求进行规则匹配生成预警记录
     * @param warningDTOs
     */
    List<Warning> reportWarning(List<WarningDTO> warningDTOs);
}
