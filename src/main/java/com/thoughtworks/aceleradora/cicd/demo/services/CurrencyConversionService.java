package com.thoughtworks.aceleradora.cicd.demo.services;

import com.thoughtworks.aceleradora.cicd.demo.domain.CurrencyCode;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.thoughtworks.aceleradora.cicd.demo.domain.CurrencyCode.BRL;
import static com.thoughtworks.aceleradora.cicd.demo.domain.CurrencyCode.USD;

@Service
public class CurrencyConversionService {

    private static final class ConversionRate {
        public final CurrencyCode from;
        public final CurrencyCode to;
        public final BigDecimal rate;

        ConversionRate(CurrencyCode from, CurrencyCode to, BigDecimal rate) {
            this.from = from;
            this.to = to;
            this.rate = rate;
        }

        boolean matches(CurrencyCode from, CurrencyCode to) {
            return this.from == from && this.to == to;
        }
    }

    private static final List<ConversionRate> RATES = List.of(
            new ConversionRate(USD, BRL, new BigDecimal("5.03")),
            new ConversionRate(BRL, USD, new BigDecimal("0.20"))
    );

    public BigDecimal convert(CurrencyCode from, CurrencyCode to, String value) {
        if (from == to) {
            return twoDecimalPlaces(new BigDecimal(value));
        }

        var rate = RATES.stream().filter(it -> it.matches(from, to)).findFirst();

        if (rate.isEmpty()) {
            throw new IllegalArgumentException("Unsupported conversion");
        }

        return twoDecimalPlaces(new BigDecimal(value).multiply(rate.get().rate));
    }

    private BigDecimal twoDecimalPlaces(BigDecimal value) {
        return value.setScale(2, RoundingMode.DOWN);
    }
}
