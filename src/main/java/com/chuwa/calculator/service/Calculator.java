package com.chuwa.calculator.service;

import com.chuwa.calculator.entity.Operation;
import com.chuwa.calculator.strategy.OperationStrategy;
import com.chuwa.calculator.util.Constants;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Calculator {
    public BigDecimal calculate(Operation operation, BigDecimal num1, BigDecimal num2) {
        if (operation == null) {
            throw new UnsupportedOperationException(Constants.UNSUPPORTED_OPERATION + null);
        }
        OperationStrategy strategy = operation.getStrategy();
        return strategy.apply(num1, num2);
    }
}
