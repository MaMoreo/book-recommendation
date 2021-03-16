package com.zenjob.bookrecommendationservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@JoinColumn(name = "user_id") 
	private User user;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;   
	private Feedback feedback; 

	Recommendation() { // JPA Only
	}

	public Recommendation(User user, Book book) {
		this.user = user;
		this.book = book;
		feedback = Feedback.NO_FEEDBACK;
	}
}
