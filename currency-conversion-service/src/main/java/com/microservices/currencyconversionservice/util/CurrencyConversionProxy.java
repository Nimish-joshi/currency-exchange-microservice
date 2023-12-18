package com.microservices.currencyconversionservice.util;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.currencyconversionservice.bean.CurrencyConversion;

//@FeignClient(name="currency-exchange", url="localhost:8000")
@FeignClient(name="currency-exchange", url="${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost:}8000")
//@FeignClient(name="currency-exchange")
public interface CurrencyConversionProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangevalue(@PathVariable String from, @PathVariable String to);
}
