package com.zenjob.bookrecommendationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
@SuppressWarnings("serial")
public class UserDuplicatedException extends RuntimeException {

	/**
	 * The user already exists in the system.
	 */
	public UserDuplicatedException(String userId) {
		super("user '" + userId + "' already exists.");
	}
}
