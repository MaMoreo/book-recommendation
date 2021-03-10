package com.zenjob.bookrecommendationservice.configuration;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.zenjob.bookrecommendationservice.model.Book;
import com.zenjob.bookrecommendationservice.model.CSVFileReader;
import com.zenjob.bookrecommendationservice.repository.BookRepository;

@Component
public class BootStrapData implements CommandLineRunner {

	private final BookRepository bookRepository;
	private final CSVFileReader csv;
	
	public BootStrapData(CSVFileReader csv, BookRepository bookRepository) {
		super();
		this.csv = csv;
		this.bookRepository = bookRepository;
	}


	@Override
	public void run(String... args) throws Exception {
		List<Book> books = csv.processBooks();
		books.stream().forEach(b -> bookRepository.save(b));
	}
}
