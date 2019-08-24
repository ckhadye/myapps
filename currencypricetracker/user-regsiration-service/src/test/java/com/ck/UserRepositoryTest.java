package com.ck;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ck.entities.Currency;
import com.ck.entities.User;
import com.ck.exceptions.UserAlreadyExistsException;
import com.ck.repositories.CurrencyRepository;
import com.ck.repositories.UserRepository;
import com.ck.services.UserService;

@RunWith(SpringRunner.class)
@DataJpaTest
//@SpringBootTest
@AutoConfigureTestDatabase
public class UserRepositoryTest {
	
    @Autowired
    private TestEntityManager entityManager;
    
//
//	@Autowired
//	private EntityManager entityManager;
//    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CurrencyRepository currencyRepository;
    
    @Before
    public void init() {
    	
    	userRepository.deleteAll();
    	currencyRepository.deleteAll();
    	
//    	entityManager.flush();
    	
    	Set<Currency> currencies = new HashSet<Currency>();
    	Currency btc = new Currency("BTC","Bitcoin");
    	Currency eth = new Currency("ETH","Ethereum");
    	Currency xrp = new Currency("XRP","Ripple");
    	Currency bch = new Currency("BCH","Bitcoin Cash");

    	btc = entityManager.persist(btc);
    	eth = entityManager.persist(eth);
    	xrp = entityManager.persist(xrp);
    	bch = entityManager.persist(bch);

//    	entityManager.persist(btc);
//    	entityManager.persist(eth);
//    	entityManager.persist(xrp);
//    	entityManager.persist(bch);
//    	currencyRepository.save(btc);
//    	currencyRepository.save(eth);
//    	currencyRepository.save(xrp);
//    	currencyRepository.save(bch);
//    	
    	currencies.add(btc);
		currencies.add(eth);
		currencies.add(xrp);
		currencies.add(bch);
    	
    	User user1 = new User("user1","password","user1@yopmail.com",null);
//    	User user2 = new User("user2","password","user2@yopmail.com",currencies);
    	User user2 = new User("user2","password","user2@yopmail.com",null);
    	
    	System.out.println("------------------"+user1.getId()+"------------------");
    	System.out.println("------------------"+user2.getId()+"------------------");
    	
    	
//    	userRepository.save(user1);
    	user1 = entityManager.persist(user1);
    	System.out.println("------------------"+user1.getId()+"------------------");
    	
    	user2 = entityManager.persist(user2);
    	System.out.println("------------------"+user2.getId()+"------------------");
    	
    	
    	assertTrue(user1.getId() != user2.getId());
    	
    	user1.setCurrencies(currencies);
    	user2.setCurrencies(currencies);
    	
    }
    
    
    @Test
    public void userIDNotNullTest() {
    	User user = userRepository.findByUserName("user1");
//    	System.out.println(user.getId());
    	assertTrue(user != null);
    	assertTrue(user.getId() != null);
    }

    
    @Test
    public void getUserByUserNameTest() {
    	User user = userRepository.findByUserName("user1");
    	assertTrue(user != null);
    	assertTrue(user.getUserName().equals("user1"));
    }

//    @Test
    public void currenciesForUserTest() {
    	User user = userRepository.findByUserName("user1");
    	Set<Currency> currencies = user.getCurrencies();
    	
    	assertTrue(currencies.size() == 4);
    	
    	boolean isCurrencyPresent = false;
    	
    	for (Currency currency : currencies) {
			if(currency.getTicker().equals("BTC")) {
				isCurrencyPresent = true;
				break;
			}
		}
    	
    	assertTrue(isCurrencyPresent);
    	
//    	assertTrue(currencies.get(0).getId() != null);
    	
//    	List<Long> currencyIDList = currencies.stream().map(currency -> {System.out.println("Currency:"+currency.getCurrencyName()+",ID:"+currency.getId());return currency.getId();}).collect(Collectors.toList());
    	
//    	for (Long id : currencyIDList) {
//    		System.out.println(id);
//		}
    }
    
   // @Test(expected=UserAlreadyExistsException.class)
    public void userRepoSaveTest() throws UserAlreadyExistsException {
    	
    	User user = new User("userByRepo", "userByRepo","userByRepo@yopmail.com", null);
    	
    	userRepository.save(user);
    	
    	User userByRepo = userRepository.findByUserName("userByRepo");
    	
    	assertNotNull(userByRepo);
    	System.out.println("USER ID :" +userByRepo.getId());
    	
//    	Saving again
    	userRepository.save(user);
    	
    	System.out.println("USER ID :" +userByRepo.getId());
    	
//    	entityManager.flush();
    	
//    	userService.registerUser(user);
    	
    }
    
//    @Test
    public void addCurrencyTest() {
    	
    	System.out.println("-----------Listing all users-----------");
    	userRepository.findAll().forEach(user -> {System.out.println(user.getUserName());});
    	System.out.println("-----------Listing over-----------");
    	User user = userRepository.findByUserName("user1");

    	assertTrue(user != null);
    	
//    	currencyRepository
    	Currency NEW = new Currency("NEW", "New currency");

    	entityManager.persist(NEW);
    	
    	NEW = currencyRepository.findByTicker("NEW");
    	
    	assertTrue(NEW != null);
    	
    	user.getCurrencies().add(NEW);
    	
    	entityManager.persist(user);
    	
    	
//    	entityManager.flush();
//    	
//    	EntityManager em = entityManager.getEntityManager();
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery<User> cq = cb.createQuery(User.class);
//		Root<User> user1 = cq.from(User.class);
//
//		cq.where(cb.isMember("BTC", user1.<List<Currency>>get("currencies").<List<String>>get("ticker")));
//		
//		CriteriaQuery<User> selectUserWithCurrency = cq.select(user1);
//		
//		TypedQuery<User> tq = em.createQuery(cq);
//		List<User> users = tq.getResultList();
//		
//		System.out.println("Users with currency");
//		for (User currUser : users) {
//			System.out.println(currUser.getUserName());
//		}
		
    	
    }
    
    @After
    public void cleanup() {
    	userRepository.deleteAll();
    	currencyRepository.deleteAll();
    }
    
}
