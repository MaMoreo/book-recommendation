package com.zenjob.bookrecommendationservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ASIN;Title;Author;Genre
 *
 * @author Miguel Moreo
 */
@Entity
@Data
@AllArgsConstructor
@Builder
public class Book {

	@Id
	@EqualsAndHashCode.Exclude 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long asin;
	private String title;
	private String author;
	private String genre;

	public Book() { // JPA Only
	}
	
}
