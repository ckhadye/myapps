package com.ck.exceptions;

public class CurrencyUserServiceException extends Exception{

	protected String exceptionMessage;
	
	public CurrencyUserServiceException(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	
}
