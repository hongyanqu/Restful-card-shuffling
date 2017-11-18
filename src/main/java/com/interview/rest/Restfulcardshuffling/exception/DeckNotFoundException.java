package com.interview.rest.Restfulcardshuffling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DeckNotFoundException extends RuntimeException{
	public DeckNotFoundException(String message) {
		super(message);
	}
}
