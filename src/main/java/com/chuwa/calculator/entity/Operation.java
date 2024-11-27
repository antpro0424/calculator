package com.chuwa.calculator.entity;

import com.chuwa.calculator.strategy.OperationStrategy;
import com.chuwa.calculator.strategy.impl.*;
import lombok.Getter;

@Getter
public enum Operation {
    ADD(new AdditionStrategy()),
    SUBTRACT(new SubtractionStrategy()),
    MULTIPLY(new MultiplicationStrategy()),
    DIVIDE(new DivisionStrategy());

    private final OperationStrategy strategy;

    Operation(OperationStrategy strategy) {
        this.strategy = strategy;
    }

}


