package com.KoreaIT.example.JAM.exception;

public class SQLErrorException extends RuntimeException { // sqlerror 예외 처리 담당
	private Exception origin;

	public SQLErrorException(String message, Exception origin) {
		super(message);
		this.origin = origin;
	}

	public Exception getOrigin() {
		return origin;
	}
}
