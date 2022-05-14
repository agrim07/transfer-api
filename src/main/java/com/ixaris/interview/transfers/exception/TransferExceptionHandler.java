package com.ixaris.interview.transfers.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Log4j2
public class TransferExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<String> internalServerErrorResponse(Exception exception, final WebRequest req) {
		log.error("RuntimeException: "+ exception.getMessage());
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
