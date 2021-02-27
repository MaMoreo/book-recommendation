package com.zenjob.bookrecommendationservice.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Test;

public class BookServiceTest {
	
	private static final int N_BOOKS = 20;

	/**
	 * Test method for
	 * {@link com.zenjob.bookrecommendationservice.model.BookService.getNBooks(Integer)}.
	 */
	@Test
	public void getNBooksTest() {
		BookService bs = new BookService();
		Set<Book> nBooks = bs.getNBooks(N_BOOKS);
		assertNotNull(nBooks);
		assertEquals(N_BOOKS, nBooks.size());
	}
}
