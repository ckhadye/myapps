package com.ck.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ck.entities.User;
import com.ck.exceptions.UserAlreadyExistsException;

//@Service
public interface UserService {

	boolean registerUser(User user) throws UserAlreadyExistsException;
	User getUserByUserName(String userName);
	
	List<User> getAllUsers();

	boolean updateUser(User user);
	
	boolean changePassword(User user, String password);
	
	void forgotPassword(String userName);
	
	void forgotUserName(String email);
	
//	TODO: User name should be replaced with JWT token or some cookie based mechanism 
	void addCurrency(String user, String ticker);
	
	void removeCurrency(String user, String ticker);
}
