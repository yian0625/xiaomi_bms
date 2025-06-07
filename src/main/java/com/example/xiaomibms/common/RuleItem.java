package com.example.xiaomibms.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//规则解析器
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuleItem {
    private int level;
    private double min;
    private double max;

    @Override
    public String toString() {
        return "RuleItem{" +
                "level=" + level +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
