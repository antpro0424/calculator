package com.chuwa.calculator.strategy;

import java.math.BigDecimal;

public interface OperationStrategy {
    BigDecimal apply(BigDecimal num1, BigDecimal num2);
}
