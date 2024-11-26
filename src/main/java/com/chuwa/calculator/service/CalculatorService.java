package com.chuwa.calculator.service;

import com.chuwa.calculator.entity.Operation;
import com.chuwa.calculator.dto.ChainRequest;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    private final Calculator calculator;

    public CalculatorService(Calculator calculator) {
        this.calculator = calculator;
    }

    public Number calculate(Operation operation, Number num1, Number num2) {
        return calculator.calculate(operation, num1, num2);
    }

    public Number chainCalculate(Number initialValue, ChainRequest request) {
        Number result = initialValue;
        for (ChainRequest.OperationItem operationItem : request.getOperations()) {
            result = calculator.calculate(operationItem.getOperation(), result, operationItem.getOperand());
        }
        return result;
    }
}

