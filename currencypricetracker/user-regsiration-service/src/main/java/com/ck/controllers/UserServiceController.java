package com.ck.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ck.dto.ResponseDTO;
import com.ck.dto.User;

@RestController
public class UserServiceController implements UserServiceEndpoint{

	@RequestMapping("/")
	public ModelAndView index()
    {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("hoa_version", "HOA v0.1");
        return mav;
    }
//	@RequestMapping("/")
//	public String index()
//	{
//		ModelAndView mav = new ModelAndView("index");
//		mav.addObject("hoa_version", "HOA v0.1");
//		return "index";
//	}
	
	@Override
	public ModelAndView registerUser(User user) {
		ResponseDTO response = populateResponse();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("register-user-response");
		mav.addObject("registration", response);
		return mav;
	}

//	@ModelAttribute("registerUserResponse")
	private ResponseDTO populateResponse() {
		ResponseDTO response = new ResponseDTO();
//		If succesful set success message else error message key from properties. Thymeleaf will resolve same at runtime.
		response.setResponseMessage("user.registration.response.success.message");
		response.setSuccess(true);
		response.setResponseCode("user.registration.error.key");
		return response;
	}
	
	@Override
	public ModelAndView addCurrency(String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView removeCurrency(String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView getUserDetails(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
}
