package com.ck.clients;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface ExchangeRateClient {

	@RequestMapping(method=RequestMethod.GET,produces= {"application/json"},path="/exchange-rates/{currency}")
	String getExchangeRate(@PathVariable(name="currency") String currency);
}
