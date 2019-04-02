package com.ck.stocksmaster.subscriber.model;

public class StockTicker {
	private String userName;
	private String ticker;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ticker:").append(ticker).append(",").append("UserName:").append(userName);
		return builder.toString();
	}
	
}
