package com.ck.stocksmaster.subscriber.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name="stockmaster-data-dev")
public interface DBServiceClient {
	@PostMapping(path="/add/{userName}/{ticker}",produces= {MediaType.TEXT_PLAIN_VALUE})
	@ResponseBody
	String addStockEntry(@PathVariable(name="userName",required=true) String userName,@PathVariable(name="ticker",required=true) String ticker);
}
