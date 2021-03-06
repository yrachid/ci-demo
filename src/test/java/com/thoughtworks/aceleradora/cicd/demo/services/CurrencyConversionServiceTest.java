package com.thoughtworks.aceleradora.cicd.demo.services;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.thoughtworks.aceleradora.cicd.demo.domain.CurrencyCode.BRL;
import static com.thoughtworks.aceleradora.cicd.demo.domain.CurrencyCode.USD;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrencyConversionServiceTest {

    private final CurrencyConversionService service = new CurrencyConversionService();

    @Test
    void whenBothCurrenciesAreTheSameTheValueIsConstant() {
        BigDecimal result = service.convert(USD, USD, "100");

        assertThat(result, equalTo(new BigDecimal("100.00")));
    }

    @Test
    void convertsWhenCurrencyIsSupported() {
        BigDecimal result = service.convert(USD, BRL, "100");

        assertEquals(new BigDecimal("503.00"), result);
    }
}