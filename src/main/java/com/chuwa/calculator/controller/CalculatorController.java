package com.chuwa.calculator.controller;

import com.chuwa.calculator.dto.CalculateRequest;
import com.chuwa.calculator.dto.ChainRequest;
import com.chuwa.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<Number> calculate(@RequestBody CalculateRequest request) {
        Number result = calculatorService.calculate(request.getOperation(), request.getNum1(), request.getNum2());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/chain")
    public ResponseEntity<Number> chainCalculate(
            @RequestParam("initialValue") Number initialValue,
            @RequestBody ChainRequest request) {
        Number result = calculatorService.chainCalculate(initialValue, request);
        return ResponseEntity.ok(result);
    }
}

