package com.example.xiaomibms.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.example.xiaomibms.common.RuleItem;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class RuleGroupParser {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将规则组 JSON 字符串解析为 RuleItem 集合
     */
    public static List<RuleItem> parseRules(String jsonRules) throws Exception {
        return objectMapper.readValue(jsonRules, new TypeReference<List<RuleItem>>() {});
    }
}
