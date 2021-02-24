package com.zenjob.bookrecommendationservice.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zenjob.bookrecommendationservice.entity.Feedback;
import com.zenjob.bookrecommendationservice.entity.Recommendation;
import com.zenjob.bookrecommendationservice.entity.User;
import com.zenjob.bookrecommendationservice.exception.RecommendationNotFoundException;
import com.zenjob.bookrecommendationservice.exception.UserDuplicatedException;
import com.zenjob.bookrecommendationservice.exception.UserNotFoundException;
import com.zenjob.bookrecommendationservice.model.Book;
import com.zenjob.bookrecommendationservice.model.BookService;
import com.zenjob.bookrecommendationservice.repository.RecommendationRepository;
import com.zenjob.bookrecommendationservice.repository.UserRepository;
import com.zenjob.bookrecommendationservice.service.UserService;

@RestController
@RequestMapping("/recommendations")
public class BookRecommendationRestController {

	@Autowired
	private RecommendationRepository recommendationRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookService bookService;
	@Autowired 
	private UserService userService;

	
	
	/**
	 * Gets all the users:
	 * 
	 * @return A collection with all the users
	 */
	@GetMapping("/users")
	public Collection<User> getAllUsers() {
		return userService.getAllUsers();
	}

	/**
	 * Version 2: with Hateoas (Not working)
	 * 
	 * @throws UserNotFoundException
	 * @return The user and links in HATEOAS format if found, <br>
	 * raises and exception otherwise
	 */
	@GetMapping("v2/users/{username}")
	public Resource<User> getUserHateoas(@PathVariable(name = "username") String userName) {
		User user = userService.findUser(userName)
				.orElseThrow(() -> new UserNotFoundException(userName));
		
		return new Resource<>(user,
				linkTo(methodOn(BookRecommendationRestController.class).getUser(userName)).withSelfRel()
				,linkTo(methodOn(BookRecommendationRestController.class).getAllUsers()).withRel("users"));
	}

	
	@GetMapping("v1/users/{username}")
	public User getUser(@PathVariable(name = "username") String userName) {
		return userService.findUser(userName)
				.orElseThrow(() -> new UserNotFoundException(userName));
	}
	
	/**
	 * Creates a user. Replies to recommendations/{userName} (POST)
	 * 
	 * @return the created entity if any
	 */
	@PostMapping("/users")
	public ResponseEntity<?> createUser(@RequestParam String userName) {
		
		userRepository.findByUsername(userName)
			.ifPresent(value -> {
				throw(new UserDuplicatedException(value.getUsername()));
			});
		
		User user = userRepository.save(new User(userName));     //create the user
		Set<Book> nBooks = bookService.getNBooks(20);           // get 20 recommendations

		List<Recommendation> recommendations = nBooks.stream()
				.map(book -> recommendationRepository.save(new Recommendation(user, book.getAsin())))
				.collect(Collectors.toList());

		if (recommendations != null) {
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					//.path("/{id}")
					.buildAndExpand(user.getId())
					.toUri();

			return ResponseEntity.created(location).build();
		}

		return ResponseEntity.badRequest().build();
	}

	/**
	 * recommendations/{userName}/{bookId}/{fback}
	 * 
	 * @param userName
	 * @param bookId
	 * @param fback
	 * @return
	 */
	@PutMapping
	ResponseEntity<?> giveFeedback(@RequestParam String userName, @RequestParam String bookId,
			@RequestParam Feedback fback) {
		
		Recommendation recommendation = recommendationRepository
				.findByUserUsernameAndBook(userName, bookId)
				.orElseThrow(() -> new RecommendationNotFoundException(userName, bookId));
		
		recommendation.setFeedback(fback);
		recommendationRepository.save(recommendation);
		
		return ResponseEntity.ok(recommendation);
	}
}
