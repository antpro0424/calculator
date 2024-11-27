package com.chuwa.calculator.service;

import com.chuwa.calculator.dto.CalculateRequest;
import com.chuwa.calculator.dto.ChainRequest;
import com.chuwa.calculator.dto.PrecisionSettings;
import com.chuwa.calculator.entity.Operation;
import com.chuwa.calculator.service.impl.CalculatorServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceTest {

    private final CalculatorServiceImpl calculatorService = new CalculatorServiceImpl();

    @Test
    public void testSingleCalculation() {
        CalculateRequest request = new CalculateRequest();
        request.setOperation(Operation.ADD);
        request.setNum1("10");
        request.setNum2("5");
        request.setPrecisionSettings(new PrecisionSettings() {{
            setScale(2);
            setRoundingMode(RoundingMode.HALF_UP);
        }});

        BigDecimal result = calculatorService.calculate(request);
        assertEquals(new BigDecimal("15.00"), result);
    }

    @Test
    public void testChainCalculation() {
        ChainRequest request = new ChainRequest();
        request.setInitialValue("10");
        request.setPrecisionSettings(new PrecisionSettings() {{
            setScale(2);
            setRoundingMode(RoundingMode.HALF_UP);
        }});
        request.setOperations(List.of(
                new ChainRequest.OperationItem() {{
                    setOperation(Operation.ADD);
                    setOperand("5");
                }},
                new ChainRequest.OperationItem() {{
                    setOperation(Operation.MULTIPLY);
                    setOperand("2");
                }},
                new ChainRequest.OperationItem() {{
                    setOperation(Operation.SUBTRACT);
                    setOperand("10");
                }}
        ));

        BigDecimal result = calculatorService.chainCalculate(request);
        assertEquals(new BigDecimal("10.00"), result);
    }
}

