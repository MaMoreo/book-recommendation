package com.zenjob.bookrecommendationservice.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String username;

	/**
	 *  mappedBy indicates the entity is the
	 *  inverse of the
	 *  relationship
	 */
	@OneToMany(mappedBy = "user")
	private Set<Recommendation> recommendations = new HashSet<>(); 
														

	/**
	 * Default Constructor for JPA
	 */
	User() {
		// JPA Only
	}

	public User(String name) {
		this.username = name;
	}
}
