package com.chuwa.calculator.dto;

import com.chuwa.calculator.entity.Operation;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CalculateRequest {
    private Operation operation;
    private String num1;
    private String num2;
    private PrecisionSettings precisionSettings;
}
