package com.ck.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ck.entities.Currency;

public interface CurrencyRepository extends CrudRepository<Currency, Long>{
	
	public Currency findByTicker(String ticker);

}
