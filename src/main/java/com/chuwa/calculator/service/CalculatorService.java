package com.chuwa.calculator.service;

import com.chuwa.calculator.dto.CalculateRequest;
import com.chuwa.calculator.dto.ChainRequest;

import java.math.BigDecimal;

public interface CalculatorService {
    BigDecimal calculate(CalculateRequest request);
    BigDecimal chainCalculate(ChainRequest request);
}

