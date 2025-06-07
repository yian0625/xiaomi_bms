package com.example.xiaomibms.util;

import com.example.xiaomibms.common.RuleItem;

public class RuleEvaluator {
    /**
     * 判断给定数值 value 是否满足 rule 规则条件。
     * @param rule 规则项对象
     * @param value 要判断的数值（例如 (Mx - Mi)）
     * @return true 表示满足规则；false 表示不满足
     */
    public static boolean evaluate(RuleItem rule, double value) {
        boolean meetMin = true;
        boolean meetMax = true;
        // 如果 min 不为 -1，则要求 value >= min
        if (rule.getMin() != -1) {
            meetMin = value >= rule.getMin();
        }
        // 如果 max 不为 -1，则要求 value < max
        if (rule.getMax() != -1) {
            meetMax = value < rule.getMax();
        }
        return meetMin && meetMax;
    }
}
