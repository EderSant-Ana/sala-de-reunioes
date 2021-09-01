package com.santana.saladereuniao.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException e, WebRequest request){
		
		ErrorDetails error = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandler(ResourceNotFoundException e, WebRequest request){
		
		ErrorDetails error = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	
	
	
}
