package com.zenjob.bookrecommendationservice.service;

import java.util.Collection;

import com.zenjob.bookrecommendationservice.model.Book;

public interface BookService {
	Collection<Book> getAllBooks();
}
