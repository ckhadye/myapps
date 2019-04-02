package com.ck.stocksmaster.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ck.stocksmaster.data.entity.StockWatch;

public interface  StockWatchDAORepository extends CrudRepository<StockWatch,Long>{
	List<StockWatch> findByStockTicker(String stockTicker);
	List<StockWatch> findByUserName(String userName);
}
