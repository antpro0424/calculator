package com.chuwa.calculator.dto;

import com.chuwa.calculator.entity.Operation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ChainRequest {
    private List<OperationItem> operations;

    @Setter
    @Getter
    public static class OperationItem {
        private Operation operation;
        private Number operand;

    }
}

