package com.chuwa.calculator.service;

import com.chuwa.calculator.entity.Operation;
import com.chuwa.calculator.util.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Calculator {
    private final Map<Operation, OperationHandler> operationHandlers = new HashMap<>();

    public Calculator() {
        registerOperation(Operation.ADD, BigDecimal::add);
        registerOperation(Operation.SUBTRACT, BigDecimal::subtract);
        registerOperation(Operation.MULTIPLY, BigDecimal::multiply);
        registerOperation(Operation.DIVIDE, (num1, num2) -> {
            if (num2.compareTo(BigDecimal.ZERO) == 0) {
                throw new ArithmeticException(Constants.DIVIDE_BY_ZERO);
            }
            return num1.divide(num2, 20, RoundingMode.HALF_UP);
        });
    }

    public void registerOperation(Operation operation, OperationHandler handler) {
        operationHandlers.put(operation, handler);
    }

    public BigDecimal calculate(Operation operation, BigDecimal num1, BigDecimal num2) {
        OperationHandler handler = operationHandlers.get(operation);
        if (handler == null) {
            throw new UnsupportedOperationException(Constants.UNSUPPORTED_OPERATION + operation);
        }
        return handler.apply(num1, num2);
    }

    @FunctionalInterface
    public interface OperationHandler {
        BigDecimal apply(BigDecimal num1, BigDecimal num2);
    }
}
