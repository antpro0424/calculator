package com.chuwa.calculator.service;

import com.chuwa.calculator.entity.Operation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Calculator {
    private final Map<Operation, OperationHandler> operationHandlers = new HashMap<>();

    public Calculator() {
        registerOperation(Operation.ADD, (num1, num2) -> num1.doubleValue() + num2.doubleValue());
        registerOperation(Operation.SUBTRACT, (num1, num2) -> num1.doubleValue() - num2.doubleValue());
        registerOperation(Operation.MULTIPLY, (num1, num2) -> num1.doubleValue() * num2.doubleValue());
        registerOperation(Operation.DIVIDE, (num1, num2) -> {
            if (num2.doubleValue() == 0) {
                throw new ArithmeticException("Cannot divide by zero");
            }
            return num1.doubleValue() / num2.doubleValue();
        });
    }

    public void registerOperation(Operation operation, OperationHandler handler) {
        operationHandlers.put(operation, handler);
    }

    public Number calculate(Operation operation, Number num1, Number num2) {
        if (!operationHandlers.containsKey(operation)) {
            throw new UnsupportedOperationException("Operation not supported: " + operation);
        }
        return operationHandlers.get(operation).apply(num1, num2);
    }

    @FunctionalInterface
    public interface OperationHandler {
        Number apply(Number num1, Number num2);
    }
}
