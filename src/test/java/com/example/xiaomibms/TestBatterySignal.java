package com.example.xiaomibms;

import com.example.xiaomibms.controller.BatterySignalController;
import com.example.xiaomibms.domain.dto.BatterySignalDTO;
import com.example.xiaomibms.domain.entity.BatterySignal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestBatterySignal {
    @Autowired
    private BatterySignalController batterySignalController;

    @Test
    public void add() {
        batterySignalController.addSignal(new BatterySignalDTO(){{
            setCarId(11L);
            setBatteryType("电池");
            setStatus(1);
            setMaxVoltage(12.0);
            setMinVoltage(11.0);
            setMaxCurrent(10.0);
            setMinCurrent(8.0);
        }});
    }

    @Test
    public void delete() {
        batterySignalController.deleteSignal(4L);
    }

    @Test
    public void update() {
        batterySignalController.updateSignal(new BatterySignalDTO(){{
            setId(8L);
            setCarId(11L);
            setBatteryType("三元电池");
            setStatus(1);
            setMaxVoltage(12.0);
            setMinVoltage(11.0);
            setMaxCurrent(10.0);
            setMinCurrent(8.0);
        }});
    }

    @Test
    public void select() {
        System.out.println(batterySignalController.getSignalById(6L));
    }
}
