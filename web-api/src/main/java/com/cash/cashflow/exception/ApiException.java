package com.cash.cashflow.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApiException extends RuntimeException {
	private HttpStatus status;
	private String code;
	private String message;

	public ApiException() {
		super();
	}

	public ApiException(Throwable ex) {
		super(ex);
	}

	public ApiException(HttpStatus status, Throwable ex) {
		this(ex);
		this.status = status;
	}

	public ApiException(HttpStatus status, String message, String code) {
		this.status = status;
		this.message = message;
		this.code = code;
	}

	public ApiException(HttpStatus status, String message, String code, Throwable ex) {
		this(ex);
		this.status = status;
		this.message = message;
		this.code = code;
	}
}
