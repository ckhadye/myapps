package com.ck.services;

import java.net.URISyntaxException;

public interface ExchangeRateService {
	String getExchangeRate(String currency) throws URISyntaxException;
}
