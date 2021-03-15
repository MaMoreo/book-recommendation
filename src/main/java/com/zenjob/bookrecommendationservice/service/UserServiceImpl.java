package com.zenjob.bookrecommendationservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenjob.bookrecommendationservice.entity.Book;
import com.zenjob.bookrecommendationservice.entity.Recommendation;
import com.zenjob.bookrecommendationservice.entity.User;
import com.zenjob.bookrecommendationservice.exception.UserDuplicatedException;
import com.zenjob.bookrecommendationservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BookService bookService;
	private final RecommendationService recommendationService;
	

	@Autowired
	public UserServiceImpl(UserRepository userRepository, BookService bookService, RecommendationService recommendationService ) {
		this.userRepository = userRepository;
		this.bookService = bookService;
		this.recommendationService = recommendationService;
	}

	@Override
	public List<User> getAllUsers() {
		Iterable<User> all = userRepository.findAll();
		List<User> result = new ArrayList<>();
		all.forEach(result::add);
		return result;
	}

	@Override
	public Optional<User> findUser(String userName) {
		return userRepository.findByUsername(userName);
	}

	@Override
	public User createUser(String userName) {
		userRepository.findByUsername(userName).ifPresent(value -> {
			throw (new UserDuplicatedException(value.getUsername()));
		});

		User user = userRepository.save(new User(userName)); // create the user
		Set<Book> nBooks = bookService.getNBooks(20); // get 20 recommendations

		Set<Recommendation> recommendations = nBooks
				.stream()
				.map(book -> recommendationService.createRecommendation(new Recommendation(user, book)))
				.collect(Collectors.toSet())
						;

		user.setRecommendations(recommendations);
		return user;
	}

}
