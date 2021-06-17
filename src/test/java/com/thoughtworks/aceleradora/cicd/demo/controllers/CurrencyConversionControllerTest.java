package com.thoughtworks.aceleradora.cicd.demo.controllers;

import com.thoughtworks.aceleradora.cicd.demo.services.CurrencyConversionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CurrencyConversionControllerTest {

    @Autowired
    private CurrencyConversionService service;

    @Test
    void returnsJsonResponseWithConversionResultAndParameters() throws Exception {
        api()
                .perform(
                        get("/convert")
                                .param("from", "USD")
                                .param("to", "USD")
                                .param("value", "100")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.from", equalTo("USD")))
                .andExpect(jsonPath("$.to", equalTo("USD")))
                .andExpect(jsonPath("$.value", equalTo("100")))
                .andExpect(jsonPath("$.result", equalTo("100,00")));
    }

    private MockMvc api() {
        return MockMvcBuilders.standaloneSetup(new CurrencyConversionController(service)).build();
    }

}