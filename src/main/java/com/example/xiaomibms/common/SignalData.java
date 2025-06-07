package com.example.xiaomibms.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//信号解析器
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignalData {
    @JsonProperty("Mx")
    private Double Mx;

    @JsonProperty("Mi")
    private Double Mi;

    @JsonProperty("Ix")
    private Double Ix;

    @JsonProperty("Ii")
    private Double Ii;
}
