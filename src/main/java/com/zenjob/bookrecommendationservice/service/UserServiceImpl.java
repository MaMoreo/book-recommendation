package com.zenjob.bookrecommendationservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenjob.bookrecommendationservice.entity.User;
import com.zenjob.bookrecommendationservice.exception.UserDuplicatedException;
import com.zenjob.bookrecommendationservice.model.Book;
import com.zenjob.bookrecommendationservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BookServiceImpl bookService;
	

	@Autowired
	public UserServiceImpl(UserRepository userRepository, BookServiceImpl bookService) {
		this.userRepository = userRepository;
		this.bookService = bookService;
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

		/*List<Recommendation> recommendations = nBooks.stream()
				.map(book -> recommendationRepository.save(new Recommendation(user, book.getAsin())))
				.collect(Collectors.toList());*/

		return user;
	}

}
