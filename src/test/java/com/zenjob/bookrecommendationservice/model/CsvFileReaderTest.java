/**
 * 
 */
package com.zenjob.bookrecommendationservice.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

/**
 * @author Miguel Moreo
 *
 */
public class CsvFileReaderTest {

	// ASIN;Title;Author;Genre
	private String bookFile = "src\\test\\resources\\books.csv";

	/**
	 * Test method for
	 * {@link com.zenjob.bookrecommendationservice.model.CSVFileReader#processBooks(String)}.
	 */
	@Test
	public void processBooksTest() {
		CSVFileReader fileReader = new CSVFileReader();
		List<Book> books = fileReader.processBooks(bookFile);
		assertTrue(books != null);
		assertTrue(books.size() == 1099);
	}
}
