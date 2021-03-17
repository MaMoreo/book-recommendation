package com.zenjob.bookrecommendationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecommendationNotFoundException extends RuntimeException{

	/**
	 * The recommendation Id was not found for the specified user.
	 */
	public RecommendationNotFoundException(String userName, Long recommendationId) {
		super("could not find recommendation '" + recommendationId + "' for '" + userName + "'.");
	}

	/**
	 * The recommendation does not exist in this system.
	 * 
	 * @param recommendationId Id was not found in the system.
	 */
	public RecommendationNotFoundException(String recommendationId) {
		super("could not find recommendation '" + recommendationId + "'.");
	}
}
