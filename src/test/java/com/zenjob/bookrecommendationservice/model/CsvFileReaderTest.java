/**
 * 
 */
package com.zenjob.bookrecommendationservice.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.zenjob.bookrecommendationservice.entity.Book;

/**
 * @author Miguel Moreo
 *
 */
class CsvFileReaderTest {

	// ASIN;Title;Author;Genre
	private String bookFile = "src/test/resources/books.csv";

	/**
	 * Test method for
	 * {@link com.zenjob.bookrecommendationservice.model.CSVFileReader#processBooks(String)}.
	 */
	@Test
	void processBooksTest() {
		CSVFileReader fileReader = new CSVFileReader();
		List<Book> books = fileReader.processBooks(bookFile);
		assertNotNull(books);
		assertEquals(1099, books.size());
	}
}
