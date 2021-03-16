package com.zenjob.bookrecommendationservice.configuration;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.zenjob.bookrecommendationservice.entity.Book;
import com.zenjob.bookrecommendationservice.entity.User;
import com.zenjob.bookrecommendationservice.model.CSVFileReader;
import com.zenjob.bookrecommendationservice.repository.BookRepository;
import com.zenjob.bookrecommendationservice.repository.UserRepository;

@Component
public class BootStrapData implements CommandLineRunner {

	private final BookRepository bookRepository;
	private final UserRepository userRepository;
	
	private final CSVFileReader csv;
	
	public BootStrapData(CSVFileReader csv, BookRepository bookRepository, UserRepository userRepository) {
		super();
		this.csv = csv;
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
	}


	@Override
	public void run(String... args) throws Exception {
		List<Book> books = csv.processBooks();
		books.stream().forEach(b -> bookRepository.save(b));
		
		userRepository.save(new User("Mike"));
	}
}
