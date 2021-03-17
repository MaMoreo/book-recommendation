package com.zenjob.bookrecommendationservice.configuration;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.zenjob.bookrecommendationservice.entity.Book;
import com.zenjob.bookrecommendationservice.entity.Feedback;
import com.zenjob.bookrecommendationservice.entity.Recommendation;
import com.zenjob.bookrecommendationservice.entity.User;
import com.zenjob.bookrecommendationservice.model.CSVFileReader;
import com.zenjob.bookrecommendationservice.repository.BookRepository;
import com.zenjob.bookrecommendationservice.repository.RecommendationRepository;
import com.zenjob.bookrecommendationservice.repository.UserRepository;

@Component
public class BootStrapData implements CommandLineRunner {

	private final BookRepository bookRepository;
	private final UserRepository userRepository;
	private final RecommendationRepository recommendationRepository;
	
	private final CSVFileReader csv;
	
	public BootStrapData(CSVFileReader csv, BookRepository bookRepository, UserRepository userRepository, RecommendationRepository recommendationRepository) {
		super();
		this.csv = csv;
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
		this.recommendationRepository = recommendationRepository;
	}


	@Override
	public void run(String... args) throws Exception {
		List<Book> books = csv.processBooks();
		books.stream().forEach(bookRepository::save);
		
		User user = new User("Mike");
		Book b1 = Book.builder().asin(2000L).title("No vas a aprender en tu puta vida").build();
		Book b2 = Book.builder().asin(2001L).title("2001: A space Odissey").build();
		
		user = userRepository.save(user);
		b1 = bookRepository.save(b1);
		b2 = bookRepository.save(b2);
		
		Feedback like = Feedback.decode("LIKED");
		
		Recommendation rec = Recommendation.builder().user(user).book(b1).feedback(like).build();
		Recommendation rec2 = Recommendation.builder().user(user).book(b2).feedback(Feedback.LIKED).build();
		recommendationRepository.save(rec);
		recommendationRepository.save(rec2);
		
	}
}
