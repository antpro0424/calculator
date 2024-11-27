package com.chuwa.calculator.strategy.impl;

import com.chuwa.calculator.strategy.OperationStrategy;
import com.chuwa.calculator.util.Constants;
import java.math.BigDecimal;

public class DivisionStrategy implements OperationStrategy {
    @Override
    public BigDecimal apply(BigDecimal num1, BigDecimal num2) {
        if (num2.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException(Constants.DIVIDE_BY_ZERO);
        }
        return num1.divide(num2, Constants.DEFAULT_SCALE, Constants.DEFAULT_ROUNDING_MODE);
    }
}
