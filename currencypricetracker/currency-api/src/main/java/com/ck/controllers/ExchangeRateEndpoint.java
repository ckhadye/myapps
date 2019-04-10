package com.ck.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface ExchangeRateEndpoint {

	@RequestMapping(method=RequestMethod.GET,produces= {"application/json"},path="/exchange-rates/{fromCurrency}")
	String getExchangeRate(@PathVariable(name="fromCurrency",required=true) String fromCurrency,@RequestParam(name="to",required=false) String toCurrency);
}
