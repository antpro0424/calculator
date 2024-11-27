package com.chuwa.calculator.service;

import com.chuwa.calculator.entity.Operation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    public void testAddition() {
        BigDecimal result = calculator.calculate(Operation.ADD, new BigDecimal("10"), new BigDecimal("5"));
        assertEquals(new BigDecimal("15"), result);
    }

    @Test
    public void testSubtraction() {
        BigDecimal result = calculator.calculate(Operation.SUBTRACT, new BigDecimal("10"), new BigDecimal("5"));
        assertEquals(new BigDecimal("5"), result);
    }

    @Test
    public void testMultiplication() {
        BigDecimal result = calculator.calculate(Operation.MULTIPLY, new BigDecimal("10"), new BigDecimal("5"));
        assertEquals(new BigDecimal("50"), result);
    }

    @Test
    public void testDivision() {
        BigDecimal result = calculator.calculate(Operation.DIVIDE, new BigDecimal("10"), new BigDecimal("2"));
        assertEquals(0, new BigDecimal("5").compareTo(result));
    }

    @Test
    public void testDivisionByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () ->
                calculator.calculate(Operation.DIVIDE, new BigDecimal("10"), BigDecimal.ZERO)
        );
        assertEquals("Cannot divide by zero.", exception.getMessage());
    }

    @Test
    public void testUnsupportedOperation() {
        Exception exception = assertThrows(UnsupportedOperationException.class, () ->
                calculator.calculate(null, new BigDecimal("10"), new BigDecimal("5"))
        );
        assertEquals("Operation not supported: null", exception.getMessage());
    }
}
