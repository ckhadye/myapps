package com.ck.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ck.dto.ResponseDTO;
import com.ck.dto.UserDTO;
import com.ck.entities.User;
import com.ck.exceptions.UserAlreadyExistsException;
import com.ck.services.UserService;

@RestController
public class UserServiceController implements UserServiceEndpoint{

	@Autowired
	private UserService userService;
	
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
	public ModelAndView registerUser(UserDTO userDTO) {
		
		User user = new User(userDTO.getUserName(),userDTO.getPassword(),userDTO.getEmail(),null);
		boolean isRegSuccess = true;
		String messageKey = "user.registration.response.success.message";
		String responseCode = "success";
		try {
			userService.registerUser(user);
		} catch (UserAlreadyExistsException e) {
			isRegSuccess = false;
			messageKey = "user.registration.response.username.exists.message";
			responseCode = "failure";
			e.printStackTrace();
		}
		
		ResponseDTO response = populateResponse(isRegSuccess,messageKey,responseCode);
		
		response.setUser(userDTO);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("register-user-response");
		mav.addObject("registration", response);
		return mav;
	}

//	@ModelAttribute("registerUserResponse")
	private ResponseDTO populateResponse(boolean isSuccess,String messageKey,String responseCode) {
		ResponseDTO response = new ResponseDTO();
//		If succesful set success message else error message key from properties. Thymeleaf will resolve same at runtime.
//		"user.registration.response.error.message"
		response.setResponseMessage(messageKey);
		response.setSuccess(isSuccess);
		//user.registration.error.key
		response.setResponseCode(responseCode);
		return response;
	}
	
	@Override
	public ModelAndView addCurrency(String currency) {
		// TODO Auto-generated method stub
		ResponseDTO response = new ResponseDTO();
		String messageKey = "user.registration.response.success.message";
		String responseCode = "success";
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("add-currency-response");
		mav.addObject("add-currency", response);
		return null;
	}

	@Override
	public ModelAndView removeCurrency(String currency) {
		ResponseDTO response = new ResponseDTO();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("remove-currency-response");
		mav.addObject("remove-currency", response);
		return null;
	}

	@Override
	public ModelAndView getUserDetails(String userName) {
		ResponseDTO response = new ResponseDTO();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user-details-response");
		mav.addObject("user-details", response);
		return null;
	}
}
