package com.chuwa.calculator.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCalculate() throws Exception {
        String requestJson = """
            {
                "operation": "ADD",
                "num1": "10",
                "num2": "5",
                "precisionSettings": {
                    "scale": 2,
                    "roundingMode": "HALF_UP"
                }
            }
        """;

        mockMvc.perform(post("/api/calculator/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String responseBody = result.getResponse().getContentAsString();
                    assertEquals("15.00", responseBody); // 手动断言返回字符串是否符合预期
                });


    }

    @Test
    public void testChainCalculate() throws Exception {
        String requestJson = """
            {
                "initialValue": "10",
                "precisionSettings": {
                    "scale": 2,
                    "roundingMode": "HALF_UP"
                },
                "operations": [
                    { "operation": "ADD", "operand": "5" },
                    { "operation": "MULTIPLY", "operand": "2" },
                    { "operation": "SUBTRACT", "operand": "10" }
                ]
            }
        """;

        mockMvc.perform(post("/api/calculator/chain")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String responseBody = result.getResponse().getContentAsString();
                    assertEquals("20.00", responseBody);
                });

    }
}
