package com.thoughtworks.aceleradora.cicd.demo.controllers;

import com.thoughtworks.aceleradora.cicd.demo.domain.CurrencyCode;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class ConversionResponse {
    private final CurrencyCode from;
    private final CurrencyCode to;
    private final String value;
    private final String result;

    public ConversionResponse(CurrencyCode from, CurrencyCode to, String value, BigDecimal result) {
        this.from = from;
        this.to = to;
        this.value = value;
        Locale LOCALE_EN = Locale.ENGLISH;
        this.result = new DecimalFormat("#.00", new DecimalFormatSymbols(LOCALE_EN)).format(result);
    }

    public CurrencyCode getFrom() {
        return from;
    }

    public CurrencyCode getTo() {
        return to;
    }

    public String getValue() {
        return value;
    }

    public String getResult() {
        return result;
    }
}
