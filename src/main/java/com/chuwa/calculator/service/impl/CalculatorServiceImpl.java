package com.chuwa.calculator.service.impl;

import com.chuwa.calculator.dto.CalculateRequest;
import com.chuwa.calculator.dto.ChainRequest;
import com.chuwa.calculator.dto.PrecisionSettings;
import com.chuwa.calculator.entity.Operation;
import com.chuwa.calculator.exception.CalculationException;
import com.chuwa.calculator.service.Calculator;
import com.chuwa.calculator.util.Constants;
import org.springframework.stereotype.Service;
import com.chuwa.calculator.service.CalculatorService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Deque;
import java.util.LinkedList;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    private final Calculator calculator = new Calculator();

    @Override
    public BigDecimal calculate(CalculateRequest request) {
        PrecisionSettings precisionSettings = getDefaultPrecisionSettings(request.getPrecisionSettings());

        try {
            BigDecimal num1 = new BigDecimal(request.getNum1());
            BigDecimal num2 = new BigDecimal(request.getNum2());

            BigDecimal result = calculator.calculate(request.getOperation(), num1, num2);

            return applyPrecision(result, precisionSettings);
        } catch (ArithmeticException e) {
            throw new CalculationException("Arithmetic error during calculation: " + e.getMessage());
        } catch (Exception e) {
            throw new CalculationException("Unexpected error during calculation: " + e.getMessage());
        }
    }


    private PrecisionSettings getDefaultPrecisionSettings(PrecisionSettings settings) {
        if (settings == null) {
            settings = new PrecisionSettings();
            settings.setScale(Constants.DEFAULT_SCALE);
            settings.setRoundingMode(Constants.DEFAULT_ROUNDING_MODE);
        } else {
            if (settings.getScale() == null || settings.getScale() < 0  ) {
                settings.setScale(Constants.DEFAULT_SCALE);
            }
            if (settings.getRoundingMode() == null) {
                settings.setRoundingMode(RoundingMode.HALF_UP); // 如果 RoundingMode 未设置，使用默认值
            }
        }
        return settings;
    }


    @Override
    public BigDecimal chainCalculate(ChainRequest request) {
        PrecisionSettings precisionSettings = getDefaultPrecisionSettings(request.getPrecisionSettings());
        try {
            Deque<BigDecimal> operandStack = new LinkedList<>();
            Deque<Operation> operatorStack = new LinkedList<>();

            BigDecimal currentValue = new BigDecimal(request.getInitialValue());
            operandStack.push(currentValue);

            for (ChainRequest.OperationItem item : request.getOperations()) {
                Operation operation = item.getOperation();
                BigDecimal operand = new BigDecimal(item.getOperand());

                if (operation == Operation.MULTIPLY || operation == Operation.DIVIDE) {
                    BigDecimal previousOperand = operandStack.pop();
                    BigDecimal result = calculator.calculate(operation, previousOperand, operand);
                    operandStack.push(result);
                } else {
                    operatorStack.push(operation);
                    operandStack.push(operand);
                }
            }

            while (!operatorStack.isEmpty()) {
                BigDecimal rightOperand = operandStack.pop();
                BigDecimal leftOperand = operandStack.pop();
                Operation operation = operatorStack.pop();

                BigDecimal result = calculator.calculate(operation, leftOperand, rightOperand);
                operandStack.push(result);
            }
            return applyPrecision(operandStack.pop(), precisionSettings);
        } catch (Exception e) {
            throw new CalculationException("Error during chain calculation: " + e.getMessage());
        }
    }


    private BigDecimal applyPrecision(BigDecimal value, PrecisionSettings settings) {
        if (settings != null) {
            return value.setScale(settings.getScale(), settings.getRoundingMode());
        }
        return value.setScale(Constants.DEFAULT_SCALE, Constants.DEFAULT_ROUNDING_MODE);
    }

}

