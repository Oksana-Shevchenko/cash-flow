package com.cash.cashflow.util;

import com.cash.cashflow.exception.ApiException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ApiException.class)
	protected ResponseEntity<ApiException> handleApiException(ApiException exception) {
		return new ResponseEntity<>(exception, exception.getStatus());
	}
}
