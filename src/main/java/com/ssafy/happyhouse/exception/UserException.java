package com.ssafy.happyhouse.exception;

public class UserException extends Exception {
	public UserException() {
		super("사용자 예외");
	}

	public UserException(String message) {
		super(message);
	}
}
