package com.ck.controllers;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ck.clients.ExchangeRateClient;
import com.ck.services.ExchangeRateService;

@RestController
public class ExchangeRateController implements ExchangeRateClient{

	@Autowired
	private ExchangeRateService exchangeRateService;
	
	public String getExchangeRate(String currency) {
		try {
			String exchangeRateJSON = exchangeRateService.getExchangeRate(currency);
			return exchangeRateJSON;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{error:"+e.getMessage()+"}";
		}
	}
	

}
