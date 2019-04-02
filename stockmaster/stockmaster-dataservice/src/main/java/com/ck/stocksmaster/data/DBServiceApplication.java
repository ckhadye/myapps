package com.ck.stocksmaster.data;

import org.hibernate.dialect.identity.MySQLIdentityColumnSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DBServiceApplication implements CommandLineRunner{
	
//	@Autowired
//    private AppConfig myConfig;

	public static void main(String[] args) {
		SpringApplication.run(DBServiceApplication.class, args);
	}

	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	}

}
