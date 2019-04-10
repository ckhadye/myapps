package com.ck.services.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ck.controllers.ExchangeRateEndpoint;
import com.ck.services.ExchangeRateService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Qualifier("exchangeRateService")
public class ExchangeRateServiceImpl implements ExchangeRateService {

	@Autowired(required=true)
	@Qualifier("restTemplateWithSSL")
//	@Qualifier("restTemplateWithoutSSL")
	private RestTemplate restTemplate;
	
	public String getExchangeRate(String fromCurrency, String toCurrency) throws URISyntaxException {
		URI url = new URI("https://api.coinbase.com/v2/exchange-rates?currency="+fromCurrency);

		System.out.println("URL:"+url.toString());
		
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		String exchangeRateJSON = response.getBody();
		
		System.out.println(exchangeRateJSON);
		
		if(toCurrency == null || toCurrency.trim().equals("")) {
			return exchangeRateJSON;
		 } 
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode root = mapper.readTree(exchangeRateJSON);
			if(root == null || root.path("data").path("rates").path(toCurrency).isMissingNode()) {
				return "{error:Invalid from or to currency}";
			}
			JsonNode toCurrencyNode = root.get("data").get("rates").get(toCurrency);
			return "{\"data\":{\"currency\":\""+fromCurrency+"\",\"rates\":{\""+toCurrency+"\":"+toCurrencyNode.asText()+"\"}}";
//			return toCurrencyNode.asText();
		
//		return response.getBody();
		} catch (IOException e) {
			e.printStackTrace();
			return "{error:Error connecting currency service due to "+e.getStackTrace()+"}";
		}
		catch (Exception e) {
			return "{error:Exception processing request due to "+e.getStackTrace()+"}";
		}
	}
}
