package com.zenjob.bookrecommendationservice.model;

import org.junit.jupiter.api.Disabled;
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
	@Disabled
	void getNBooksTest() {
		/*BookServiceImpl bs = new BookServiceImpl(bookFile);
		Set<Book> nBooks = bs.getNBooks(N_BOOKS);
		assertNotNull(nBooks);
		assertEquals(N_BOOKS, nBooks.size());*/
	}
}
