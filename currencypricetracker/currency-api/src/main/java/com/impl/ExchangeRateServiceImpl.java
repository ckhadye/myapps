package com.ck.services.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ck.clients.ExchangeRateClient;
import com.ck.services.ExchangeRateService;

@Service
@Qualifier("exchangeRateService")
public class ExchangeRateServiceImpl implements ExchangeRateService {

	@Autowired(required=true)
	@Qualifier("restTemplateWithSSL")
//	@Qualifier("restTemplateWithoutSSL")
	private RestTemplate restTemplate;
	
	public String getExchangeRate(String currency) throws URISyntaxException {
		URI url = new URI("https://api.coinbase.com/v2/exchange-rates?currency=BTC");
//		URI url = new URI("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest");

//		URI url = new URI("https://www.capgemini.com/");
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//		ResponseEntity<String> response = restTemplate.getForEntity("https://api.coinbase.com/v2/exchange-rates?currency=BTC", String.class);
		
		return response.getBody();
		
//		return "Received request for "+currency;
		
	}

}
