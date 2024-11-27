package com.chuwa.calculator.service;

import com.chuwa.calculator.entity.Operation;
import com.chuwa.calculator.strategy.OperationStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Calculator {
    public BigDecimal calculate(Operation operation, BigDecimal num1, BigDecimal num2) {
        OperationStrategy strategy = operation.getStrategy();
        return strategy.apply(num1, num2);
    }
}
