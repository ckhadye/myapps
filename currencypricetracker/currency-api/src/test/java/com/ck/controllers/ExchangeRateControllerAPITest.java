package com.ck.controllers;
import java.awt.print.Printable;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.ck.services.ExchangeRateService;

@RunWith(SpringRunner.class)
@WebMvcTest(ExchangeRateController.class)
public class ExchangeRateControllerAPITest {
	
	@MockBean
	ExchangeRateService exchangeRateService;
	
	@Autowired
	private MockMvc mvc;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		try {
			Mockito.when(exchangeRateService.getExchangeRate(Mockito.anyString(),Mockito.anyString())).thenReturn("hello");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test1() throws Exception {
		mvc.perform(get("/exchange-rates/BTC").accept(MediaType.APPLICATION_JSON)).andDo((result) -> print(result));
		
	}

	private void print(MvcResult result) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		System.out.println(result.getResponse().getContentAsString());
	}
	
}
