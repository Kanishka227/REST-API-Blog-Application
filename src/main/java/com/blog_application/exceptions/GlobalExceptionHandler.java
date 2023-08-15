package com.blog_application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog_application.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ApiResponse> resNotFouExc(ResourceNotFound ex){
		
		String message = ex.getMessage();
		ApiResponse res = new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(res,HttpStatus.NOT_FOUND);
	}
}
