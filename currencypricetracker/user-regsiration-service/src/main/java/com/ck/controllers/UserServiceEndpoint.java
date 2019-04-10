package com.ck.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ck.dto.User;

@RequestMapping("/user-service")
public interface UserServiceEndpoint {

//	@PostMapping(path="/register",consumes= {MediaType.APPLICATION_JSON_VALUE}, produces=MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(path="/register",consumes= {MediaType.APPLICATION_JSON_VALUE})
	ModelAndView registerUser(User user);
	
	@PutMapping(path="/add/{currency}")
	ModelAndView addCurrency(@PathVariable(name="currency",required=true) String currency);
	
	@GetMapping(path="/add/{currency}",produces= {MediaType.APPLICATION_JSON_VALUE})
	ModelAndView removeCurrency(@PathVariable(name="currency",required=true)String currency);
	
	@GetMapping(path="user/{userName}",produces= {MediaType.APPLICATION_JSON_VALUE})
	ModelAndView getUserDetails(@PathVariable(name="userName",required=true) String userName);
}
 