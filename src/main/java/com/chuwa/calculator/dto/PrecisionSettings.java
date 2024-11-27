package com.chuwa.calculator.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.RoundingMode;

@Setter
@Getter
public class PrecisionSettings {
    private Integer scale;
    private RoundingMode roundingMode;
}
