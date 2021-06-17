package com.thoughtworks.aceleradora.cicd.demo.controllers;

import com.thoughtworks.aceleradora.cicd.demo.domain.CurrencyCode;
import com.thoughtworks.aceleradora.cicd.demo.services.CurrencyConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    private final CurrencyConversionService service;

    public CurrencyConversionController(CurrencyConversionService service) {
        this.service = service;
    }

    @GetMapping("/convert")
    public ResponseEntity<ConversionResponse> convert(
            @RequestParam CurrencyCode from,
            @RequestParam CurrencyCode to,
            @RequestParam String value) {

        BigDecimal result = service.convert(from, to, value);

        var response = new ConversionResponse(from, to, value, result);

        return ResponseEntity.ok(response);
    }
}