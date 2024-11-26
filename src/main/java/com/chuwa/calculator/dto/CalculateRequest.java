package com.chuwa.calculator.dto;

import com.chuwa.calculator.entity.Operation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculateRequest {
    private Operation operation;
    private Number num1;
    private Number num2;

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Number getNum1() {
        return num1;
    }

    public void setNum1(Number num1) {
        this.num1 = num1;
    }

    public Number getNum2() {
        return num2;
    }

    public void setNum2(Number num2) {
        this.num2 = num2;
    }
}
