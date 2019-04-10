package com.ck.controllers;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.json.Jackson2CodecSupport;
import org.springframework.web.bind.annotation.RestController;

import com.ck.services.ExchangeRateService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ExchangeRateController implements ExchangeRateEndpoint{

	@Autowired
	private ExchangeRateService exchangeRateService;
	
	public String getExchangeRate(String fromCurrency,String toCurrency) {
		try {
			if(fromCurrency == null || fromCurrency.trim().equals("")) {
				return "{error:From currency can not be empty}";
			}
			String exchangeRateJSON = exchangeRateService.getExchangeRate(fromCurrency,toCurrency);
			return exchangeRateJSON;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return "{error:"+e.getMessage()+"}";
		}
	}
	

}
