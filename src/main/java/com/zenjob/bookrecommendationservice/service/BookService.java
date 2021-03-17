package com.zenjob.bookrecommendationservice.service;

import java.util.Collection;
import java.util.Set;

import com.zenjob.bookrecommendationservice.entity.Book;

public interface BookService {
	
	/**
	 * Returns n books, duplicates are not allowed
	 * 
	 * @param n the number of books to retrieve
	 */
	Set<Book> getNBooks(Integer n);
	
	Collection<Book> getAllBooks();
}
