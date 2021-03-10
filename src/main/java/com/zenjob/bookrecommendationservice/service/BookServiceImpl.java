package com.zenjob.bookrecommendationservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.zenjob.bookrecommendationservice.model.Book;
import com.zenjob.bookrecommendationservice.repository.BookRepository;


@Service
public class BookServiceImpl implements BookService{

	//private String bookFile = "src\\main\\resources\\data\\books.csv"; 
	private List<Book> books;
	private final BookRepository bookRepository;

	/*public BookServiceImpl() {
		init();
	}*/

	/*public BookServiceImpl(String bookFile) {
		this.bookFile = bookFile;
		init();
	}*/
	
	
	public BookServiceImpl(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	// Loads the books in memory
	/*private void init() {
		CSVFileReader bookReader = new CSVFileReader();  //FIXME: use a Bean here!!
		books = bookReader.processBooks(bookFile);
	}*/

	/**
	 * Returns n books, duplicates are not allowed
	 * 
	 * @param n the number of books to retrieve
	 */
	public Set<Book> getNBooks(Integer n) {
		Set<Book> nRandomBooks = new HashSet<>();
		do {
			nRandomBooks.add(books.get(getRandomNumber()));
		} while(nRandomBooks.size() < n);
		
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

	@Override
	public Collection<Book> getAllBooks() {
		Iterable<Book> all = bookRepository.findAll();
		List<Book> result = new ArrayList<>();
		all.forEach(result::add);
		return result;
	}
}
