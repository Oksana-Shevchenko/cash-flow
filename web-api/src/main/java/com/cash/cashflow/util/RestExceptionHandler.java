package com.cash.cashflow.util;

import com.cash.cashflow.exception.ApiException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ApiException.class)
	protected ResponseEntity<ExceptionResponse> handleApiException(ApiException exception) {
		return ResponseEntity.status(exception.getStatus()).body(constructExceptionResponse(exception));
	}

	private ExceptionResponse constructExceptionResponse(ApiException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setCode(exception.getCode());
		exceptionResponse.setMessage(exception.getMessage());
		exceptionResponse.setStatus(exception.getStatus());
		return exceptionResponse;
	}
}

@Getter
@Setter
class ExceptionResponse {
	private HttpStatus status;
	private String code;
	private String message;
}