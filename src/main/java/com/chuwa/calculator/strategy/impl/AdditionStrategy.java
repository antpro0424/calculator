package com.chuwa.calculator.strategy.impl;

import com.chuwa.calculator.strategy.OperationStrategy;

import java.math.BigDecimal;

public class AdditionStrategy implements OperationStrategy {
    @Override
    public BigDecimal apply(BigDecimal num1, BigDecimal num2) {
        return num1.add(num2);
    }
}
