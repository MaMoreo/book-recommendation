package com.zenjob.bookrecommendationservice.model;

import lombok.Data;

/**
 * ASIN;Title;Author;Genre
 *
 * @author Miguel Moreo
 */
@Data
public class Book {
	private String asin;   //FIXME: number?
	private String title;
	private String author; //FIXME what about several authors ?
	private String genre;
	
	public Book withAsin(String asin){
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
