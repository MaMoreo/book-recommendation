package com.zenjob.bookrecommendationservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.Test;

class BookServiceTest {

	private final int N_BOOKS = 20;

	// ASIN;Title;Author;Genre
	private String bookFile = "src/test/resources/books.csv";

	/**
	 * Test method for
	 * {@link com.zenjob.bookrecommendationservice.model.BookService.getNBooks(Integer)}.
	 */
	@Test
	void getNBooksTest() {
		BookService bs = new BookService(bookFile);
		Set<Book> nBooks = bs.getNBooks(N_BOOKS);
		assertNotNull(nBooks);
		assertEquals(N_BOOKS, nBooks.size());
	}
}
