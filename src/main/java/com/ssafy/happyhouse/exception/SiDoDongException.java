package com.ssafy.happyhouse.exception;

public class SiDoDongException extends Exception {
	public SiDoDongException() {
		super("지역 예외");
	}

	public SiDoDongException(String message) {
		super(message);
	}
}
