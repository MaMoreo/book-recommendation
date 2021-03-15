package com.zenjob.bookrecommendationservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * ASIN;Title;Author;Genre
 *
 * @author Miguel Moreo
 */
@Entity
@Data
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long asin;
	private String title;
	private String author; 
	private String genre;
	
	public Book() { // JPA Only
	}
	
	public Book withAsin(Long asin){
		this.setAsin(asin);
		return this;
	}
	
	public Book withTitle(String title){
		this.setTitle(title);
		return this;
	}
	
	public Book withAuthor(String author){
		this.setAuthor(author);
		return this;
	}
	
	public Book withGenre(String genre){
		this.setGenre(genre);
		return this;
	}
}