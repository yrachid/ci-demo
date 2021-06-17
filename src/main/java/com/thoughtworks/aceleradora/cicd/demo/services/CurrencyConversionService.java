package com.thoughtworks.aceleradora.cicd.demo.services;

import com.thoughtworks.aceleradora.cicd.demo.domain.CurrencyCode;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CurrencyConversionService {

    public BigDecimal convert(CurrencyCode from, CurrencyCode to, String value) {
        if (from == to) {
            return new BigDecimal(value).setScale(2, RoundingMode.DOWN);
        }

        return BigDecimal.ONE;
    }
}
