package com.ck.exceptions;

public class UserAlreadyExistsException extends CurrencyUserServiceException {

	public UserAlreadyExistsException() {
		super("blank");
	}
	public UserAlreadyExistsException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
