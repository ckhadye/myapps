package com.ck.stocksmaster.subscriber;

import org.hibernate.dialect.identity.MySQLIdentityColumnSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SubscriberApplication implements CommandLineRunner{
	
	@Autowired
    private AppConfig myConfig;

	public static void main(String[] args) {
		SpringApplication.run(SubscriberApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(myConfig.getSpring_Profiles());
		System.out.println(myConfig.getEnvironment());;
		System.out.println(myConfig.getName());
		System.out.println(myConfig.getPort());
		System.out.println(myConfig.getContextPath());
//		System.out.println(myConfig.getServer());
	}
	
}
