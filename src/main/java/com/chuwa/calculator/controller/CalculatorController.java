package com.chuwa.calculator.controller;

import com.chuwa.calculator.dto.CalculateRequest;
import com.chuwa.calculator.dto.ChainRequest;
import com.chuwa.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {
    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<BigDecimal> calculate(@RequestBody CalculateRequest request) {
        return ResponseEntity.ok(calculatorService.calculate(request));
    }

    @PostMapping("/chain")
    public ResponseEntity<BigDecimal> chainCalculate(@RequestBody ChainRequest request) {
        return ResponseEntity.ok(calculatorService.chainCalculate(request));
    }
}
