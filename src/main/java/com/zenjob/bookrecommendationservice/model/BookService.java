package com.zenjob.bookrecommendationservice.model;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;


@Service
public class BookService {

	private String bookFile = "src\\main\\resources\\data\\books.csv"; // FIXME: parameter in builder?
	private List<Book> books;

	public BookService() {
		init();
	}

	// Loads the books in memory
	private void init() {
		CSVFileReader bookReader = new CSVFileReader();
		books = bookReader.processBooks(bookFile);
	}

	/**
	 * Returns n books, duplicates are not allowed
	 * 
	 * @param n the number of books to retrieve
	 */
	public Set<Book> getNBooks(Integer n) {
		Set<Book> nRandomBooks = new HashSet<Book>();
		for (int i = 0; i < n; i++) {
			nRandomBooks.add(books.get(getRandomNumber()));
		}
		return nRandomBooks;
	}

	// nextInt is normally exclusive of the top value,
	// so add 1 to make it inclusive
	private Integer getRandomNumber() {
		return ThreadLocalRandom.current().nextInt(0, books.size() + 1);
	}
	
	/**
	 * Just a workaround to find a book in the list.
	 * 
	 * @param asin
	 * @return
	 */
	public Optional<String> findBookByAsin(String asin) {
		return books.stream()
				.map(Book::getAsin)
				.filter(a -> a.equals(asin))
				.findFirst();
	}
}
