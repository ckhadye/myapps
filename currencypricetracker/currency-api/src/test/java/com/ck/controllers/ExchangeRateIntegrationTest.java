package com.ck.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ck.services.ExchangeRateService;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExchangeRateIntegrationTest {

	@Autowired
	private ExchangeRateController exchangeRateController;

	@MockBean
	private ExchangeRateService exchangeRateService;
	
	private String jsonResponseAsStr;

	@Before
	public void init() throws URISyntaxException, IOException {
		File jsonResponse = new ClassPathResource("response.json").getFile();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(jsonResponse);
		
		ObjectWriter writerWithDefaultPrettyPrinter = mapper.writerWithDefaultPrettyPrinter();
		jsonResponseAsStr = writerWithDefaultPrettyPrinter.writeValueAsString(root);
		
//		System.out.println("ValueAsString:"+valueAsString);
		
//		BufferedReader reader = new BufferedReader(new FileReader(jsonResponse));
//		StringBuffer strBuffer = new StringBuffer();
//		char[] charBuffer = new char[100];
//		while(reader.read(charBuffer) > 0) {
//			strBuffer.append(charBuffer);
//		}
//		
//		String responseStr = strBuffer.toString();
//		
//		System.out.println(responseStr);
		
		BDDMockito.given(exchangeRateService.getExchangeRate(Mockito.anyString(),Mockito.anyString())).willReturn(jsonResponseAsStr);
	}
	
	@Test
	public void test1() {
		String exchangeRateResponse = exchangeRateController.getExchangeRate("BTC", "");
		assertTrue(jsonResponseAsStr.equals(exchangeRateResponse));
	}

//	@Test
//	public void test2() {
//		String exchangeRateResponse = exchangeRateController.getExchangeRate("BTC", "USD");
//		System.out.println("exchangeRateResponse::::::::");
//		System.out.println(exchangeRateResponse);
//		assertTrue(exchangeRateResponse.equals("{\"data\":{\"currency\":\"BTC\",\"rates\":{\"USD\":\"111\"}}"));
//	}
	
}
