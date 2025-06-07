package com.example.xiaomibms;

import com.example.xiaomibms.controller.WarningController;
import com.example.xiaomibms.domain.dto.WarningDTO;
import com.example.xiaomibms.domain.entity.Warning;
import com.example.xiaomibms.service.WarningService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TestWarning {

    @Autowired
    private WarningController warningController;

    @Test
    void select(){
        System.out.println(warningController.getWarningsByCarId(1L));
    }
    @Test
    void report(){
        List<WarningDTO> warningDTOS = new ArrayList<>();
        WarningDTO warningDTO1 = new WarningDTO();
        warningDTO1.setCarId(1L);
        warningDTO1.setWarnId(1L);
        warningDTO1.setSignal("{\"Mx\":12.0,\"Mi\":0.6}");
        warningDTOS.add(warningDTO1);
        WarningDTO warningDTO2 = new WarningDTO();
        warningDTO2.setCarId(2L);
        warningDTO2.setWarnId(2L);
        warningDTO2.setSignal("{\"Ix\":12.0,\"Ii\":11.7}");
        warningDTOS.add(warningDTO2);
        WarningDTO warningDTO3 = new WarningDTO();
        warningDTO3.setCarId(3L);
        warningDTO3.setSignal("{\"Mx\":11.0,\"Mi\":9.6,\"Ix\":12.0,\"Ii\":11.7}");
        warningDTOS.add(warningDTO3);
        System.out.println(warningController.reportWarning(Arrays.asList(warningDTOS.toArray(new WarningDTO[warningDTOS.size()]))));
    }
}
