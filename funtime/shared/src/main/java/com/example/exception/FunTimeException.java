package com.example.exception;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
public class FunTimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpStatus errorCode;
	private String errorMessage;
	
	public FunTimeException() {
		// TODO Auto-generated constructor stub
	}

	public FunTimeException(String errorMessage) {
		super();
		this.errorCode = HttpStatus.NOT_FOUND;
		this.errorMessage = errorMessage;
	}
	
	public FunTimeException(HttpStatus errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	
}
