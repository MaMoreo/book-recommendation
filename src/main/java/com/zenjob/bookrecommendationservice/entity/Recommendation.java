package com.zenjob.bookrecommendationservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Recommendation {

	@Id
	@GeneratedValue
	private Long id;

	@JsonIgnore
	@ManyToOne
	private User user;

	private String book;   // FIXME: make this a book ?
	private Feedback feedback; 

	Recommendation() { // JPA Only
	}

	public Recommendation(User user, String book) {
		this.user = user;
		this.book = book;
	}
}
