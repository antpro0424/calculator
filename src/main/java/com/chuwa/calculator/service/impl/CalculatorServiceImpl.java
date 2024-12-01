package com.chuwa.calculator.service.impl;

import com.chuwa.calculator.dto.CalculateRequest;
import com.chuwa.calculator.dto.ChainRequest;
import com.chuwa.calculator.dto.PrecisionSettings;
import com.chuwa.calculator.entity.Operation;
import com.chuwa.calculator.exception.CalculationException;
import com.chuwa.calculator.service.Calculator;
import com.chuwa.calculator.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chuwa.calculator.service.CalculatorService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    private final Calculator calculator;

    @Autowired
    public CalculatorServiceImpl (Calculator calculator) {
        this.calculator = calculator;
    }

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
                settings.setRoundingMode(RoundingMode.HALF_UP);
            }
        }
        return settings;
    }


    @Override
    public BigDecimal chainCalculate(ChainRequest request) {
        PrecisionSettings precisionSettings = getDefaultPrecisionSettings(request.getPrecisionSettings());
        try {
            BigDecimal initialValue = new BigDecimal(request.getInitialValue());
            if (request.getOperations().isEmpty()) {
                return applyPrecision(initialValue, precisionSettings);
            }
            BigDecimal result = initialValue;

            for (ChainRequest.OperationItem item : request.getOperations()) {
                Operation operation = item.getOperation();
                BigDecimal operand = new BigDecimal(item.getOperand());
                result = calculator.calculate(operation, result, operand);
            }
            return applyPrecision(result, precisionSettings);
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

