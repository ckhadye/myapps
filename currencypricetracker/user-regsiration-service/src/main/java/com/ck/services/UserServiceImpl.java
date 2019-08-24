package com.ck.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ck.entities.Currency;
import com.ck.entities.User;
import com.ck.exceptions.UserAlreadyExistsException;
import com.ck.repositories.UserRepository;

@Service
@Qualifier("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public boolean registerUser(User user) throws UserAlreadyExistsException {
//		userRepository.save(user);
		
		if(userRepository.findByUserName(user.getUserName()) != null) {
			throw new UserAlreadyExistsException(user.getUserName() + " is already taken");
		}
		
		User userEntity = userRepository.save(user);
		
		if(userEntity == null) {
			return false;
		}
		return true;
	}

	@Override
	public User getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changePassword(User user, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void forgotPassword(String userName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forgotUserName(String email) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void addCurrency(String userName, String ticker) {
		User user = userRepository.findByUserName(userName);
//		Adding currency
		user.getCurrencies().add(new Currency(ticker, ticker+"Currency"));
//		entityManager.flush();
//		
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<User> cq = cb.createQuery(User.class);
//		Root<User> user1 = cq.from(User.class);
//
//		cq.where(cb.isMember(ticker, user1.<List<Currency>>get("currencies").<List<String>>get("ticker")));
//		
//		CriteriaQuery<User> selectUserWithCurrency = cq.select(user1);
//		
//		TypedQuery<User> tq = entityManager.createQuery(cq);
//		List<User> users = tq.getResultList();
//		
//		System.out.println("Users with currency");
//		for (User currUser : users) {
//			System.out.println(currUser.getUserName());
//		}
//		
//		TODO : Check criteria apis instead of streaming
//		boolean currencyFound = user.getCurrencies().stream().map(result -> result.getTicker()).anyMatch(result -> result.equals(ticker));
		
//		if(!currencyFound) {
////			TODO: Perform a call to coinbase to get currency details based on ticker
//			user.getCurrencies().add(new Currency(ticker, ticker+"Currency"));
//		}
	}

	@Override
	public void removeCurrency(String userName, String ticker) {
		// TODO Auto-generated method stub
	}
}
