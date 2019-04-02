package com.ck.stocksmaster.subscriber.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STOCKWATCH")
public class StockWatch {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id",unique=true,updatable=false)
	private Long id;
	
	@Column(name="ticker",nullable=false,columnDefinition="varchar(5)")
	private String stockTicker;
	
	@Column(name="username",nullable=false,columnDefinition="varchar(10)")
	private String userName;
	
	public StockWatch() {
		
	}
	
	public StockWatch(String stockTicker,String userName) {
		this.setStockTicker(stockTicker);
		this.setUserName(userName);
	}

	public String getStockTicker() {
		return stockTicker;
	}

	public void setStockTicker(String stockTicker) {
		this.stockTicker = stockTicker;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
