package com.zenjob.bookrecommendationservice.entity;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Feedback {
	LIKED, DISLIKED, NOT_INTERESTED, NO_FEEDBACK;

	@Override
	public String toString() {
	    return this.name();
	}
	
	@JsonCreator
	public static Feedback decode(final String code) {
		return Stream.of(Feedback.values()).filter(targetEnum -> targetEnum.name().equals(code)).findFirst().orElse(null);
	}
	
	//Serializing an Enum to JSON  (for GET Operations)
	@JsonValue
	public String getCode() {
		return name();
	}
}
