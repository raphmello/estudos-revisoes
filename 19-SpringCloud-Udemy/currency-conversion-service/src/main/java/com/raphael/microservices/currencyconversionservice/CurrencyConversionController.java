package com.raphael.microservices.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy proxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity) {
        ResponseEntity<CurrencyConversion> entity =
                new RestTemplate().getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}",
                        CurrencyConversion.class,
                        from,
                        to);
        CurrencyConversion currencyConversion = entity.getBody();
        assert currencyConversion != null;
        currencyConversion.setQuantity(quantity.multiply(currencyConversion.getConversionMultiple()));
        return currencyConversion;
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity) {
        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);
        currencyConversion.setQuantity(quantity.multiply(currencyConversion.getConversionMultiple()));
        return currencyConversion;
    }
}
