package com.ssafy.happyhouse.exception;

/** 아파트 예외 클래스 */
public class ApartmentDealException extends Exception {
	public ApartmentDealException() {
		super("아파트 거래 예외");
	}

	public ApartmentDealException(String message) {
		super(message);
	}
}
