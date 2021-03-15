package com.zenjob.bookrecommendationservice.service;

import java.util.Collection;

import com.zenjob.bookrecommendationservice.entity.Book;

public interface BookService {
	Collection<Book> getAllBooks();
}
