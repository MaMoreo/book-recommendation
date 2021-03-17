package com.zenjob.bookrecommendationservice.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zenjob.bookrecommendationservice.entity.Book;
import com.zenjob.bookrecommendationservice.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	public BookServiceImpl(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	/**
	 * Returns N books.
	 * 
	 * If the total amount of books in the system is bigger than N
	 * a Set of random books is returned.
	 * 
	 * Otherwise all the books as a Set are returned. (No duplicates)
	 * 
	 */
	@Override
	public Set<Book> getNBooks(Integer n) {

		List<Book> allBooks = (List<Book>) bookRepository.findAll();
		int totalBooks = allBooks.size();

		if (n < totalBooks) {
			Set<Book> nRandomBooks = new HashSet<>();
			do {
				nRandomBooks.add(allBooks.get(getRandomNumber(totalBooks)));
			} while (nRandomBooks.size() < n);

			return nRandomBooks;
		}

		return allBooks.stream().collect(Collectors.toSet());
	}

	private int getRandomNumber(int bound) {
		return ThreadLocalRandom.current().nextInt(0, bound);
	}

	// FIXME @Cache this
	@Override
	public Collection<Book> getAllBooks() {
		return (Collection<Book>) bookRepository.findAll();
	}
}
