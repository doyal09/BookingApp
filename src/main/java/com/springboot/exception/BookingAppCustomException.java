package com.springboot.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
/*
 * Customised exception class to handle exceptions
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BookingAppCustomException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public BookingAppCustomException(String exception) {
	    super(exception);
	  }

}
