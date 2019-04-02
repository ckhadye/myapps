package com.ck.stocksmaster.subscriber.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ck.stocksmaster.subscriber.clients.DBServiceClient;
import com.ck.stocksmaster.subscriber.dao.StockWatchDAORepository;
import com.ck.stocksmaster.subscriber.entity.StockWatch;
import com.ck.stocksmaster.subscriber.model.StockTicker;

@RestController
public class SubscriberController {

	@Autowired
	private DBServiceClient dbServiceClient;
	
	@Autowired
	private StockWatchDAORepository stockWatchRepository;
	
	@Autowired
	private RestTemplate restTemplate;
//	
	@RequestMapping(path="/add",produces= {MediaType.TEXT_PLAIN_VALUE})
	public @ResponseBody String addStockToWatchList(@RequestBody StockTicker stock) {
		
//		RestTemplate restTemplate = new RestTemplate();
		System.out.println("Received request to add stock");
		System.out.println(stock);
		StockWatch entity = new StockWatch(stock.getTicker(),stock.getUserName());
		
		String response = dbServiceClient.addStockEntry(stock.getUserName(), stock.getTicker());
		
		System.out.println("Response from db service:"+response);
		
//		stockWatchRepository.save(entity);
		
		List<StockWatch> stockList = stockWatchRepository.findByUserName(stock.getUserName());
		
		StringBuilder builder = new StringBuilder();
		List<String> collect = stockList.stream().map(entry -> entry.getStockTicker()).collect(Collectors.toList());
		
//		String url = "http://localhost:8082/stockmaster-data-dev/add/"+stock.getUserName()+"/"+stock.getTicker();
//		ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
//		
//		System.out.println("Response from db service:"+response.getBody());
		
		return "Your stock list:"+collect;
		
	}
	
	@RequestMapping("/remove")
	public void removeStockFrom3WatchList(@RequestBody StockTicker stock) {
		System.out.println("Received request to remove stock");
		System.out.println(stock);
	}
	
}
