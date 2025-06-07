package com.example.xiaomibms;

import com.example.xiaomibms.controller.VehicleController;
import com.example.xiaomibms.domain.dto.VehicleDTO;
import com.example.xiaomibms.domain.entity.Vehicle;
import com.example.xiaomibms.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestVehicle {

    @Autowired
    private VehicleController vehicleController;

    @Test
    void add(){
        vehicleController.addVehicle(
                new VehicleDTO(){
                    {
                        setFrameNumber(4L);
                        setBatteryHealth(100.0);
                        setBatteryType("电池");
                        setTotalMileage(1000.0);
                    }
                }
        );
    }

    @Test
    void update(){
        vehicleController.updateVehicle(
                new VehicleDTO(){
                    {
                        setId(1L);
                        setFrameNumber(11L);
                        setBatteryHealth(300.0);
                        setBatteryType("电池");
                        setTotalMileage(1000.0);
                    }
                }
        );
    }

    @Test
    void delete(){
        vehicleController.deleteVehicle("74e04cca0d6c4a01");
    }
    @Test
    void findByVid(){
        System.out.println(vehicleController.getVehicle("74e04cca0d6c4a01"));
    }
}
