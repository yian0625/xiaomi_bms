package com.example.xiaomibms.util;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class VehicleIdGenerator {
    //使用uuid生成16位vid
    public String createVid() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }
}