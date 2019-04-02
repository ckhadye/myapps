package com.ck.stocksmaster.data;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockDBController {
	
	@PostMapping(path="/add/{userName}/{ticker}",produces= {MediaType.TEXT_PLAIN_VALUE})
	@ResponseBody
	public String addStockEntry(@PathVariable(name="userName",required=true) String userName,@PathVariable(name="ticker",required=true) String ticker) {
		System.out.println("Persisting stock in stockwatch table");
		return "success";
	}

}
