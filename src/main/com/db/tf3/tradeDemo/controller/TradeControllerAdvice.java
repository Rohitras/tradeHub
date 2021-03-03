package com.db.tf3.tradeDemo.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.db.tf3.tradeDemo.exception.ValidationFailedException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class TradeControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ValidationFailedException.class)
	public ResponseEntity<HttpStatus> notFoundException(final ValidationFailedException e) {
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<HttpStatus> assertionException(final IllegalArgumentException e) {
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);
	}

}
