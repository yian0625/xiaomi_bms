package com.example.xiaomibms.util;

import com.example.xiaomibms.common.SignalData;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SignalParser {
    /**
     * 将信号组 JSON 字符串解析为 SignalData 集合
     */
    public static SignalData parseSignal(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, SignalData.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}