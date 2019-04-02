package com.ck.stocksmaster.subscriber.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ck.stocksmaster.subscriber.entity.StockWatch;

import org.springframework.data.repository.CrudRepository;

public interface  StockWatchDAORepository extends CrudRepository<StockWatch,Long>{
	List<StockWatch> findByStockTicker(String stockTicker);
	List<StockWatch> findByUserName(String userName);
}
