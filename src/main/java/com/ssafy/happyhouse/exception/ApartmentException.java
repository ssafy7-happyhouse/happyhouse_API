package com.ssafy.happyhouse.exception;

/** 아파트 예외 클래스 */
public class ApartmentException extends Exception {
	public ApartmentException() {
		super("아파트 예외");
	}

	public ApartmentException(String message) {
		super(message);
	}
}
