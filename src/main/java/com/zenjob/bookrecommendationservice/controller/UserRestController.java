package com.zenjob.bookrecommendationservice.controller;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zenjob.bookrecommendationservice.command.UserDTO;
import com.zenjob.bookrecommendationservice.entity.User;
import com.zenjob.bookrecommendationservice.exception.UserNotFoundException;
import com.zenjob.bookrecommendationservice.service.UserService;

@RestController
@RequestMapping("recommendations")
public class UserRestController {

	private final UserService userService;

	public UserRestController(UserService userService) {
		super();
		this.userService = userService;
	}

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
	 * Version 2: with HATEOAS (Not working)
	 * 
	 * @throws UserNotFoundException
	 * @return The user and links in HATEOAS format if found, <br>
	 *         raises and exception otherwise
	 */
	/*
	 * @GetMapping("v2/users/{username}") public Resource<User>
	 * getUserHateoas(@PathVariable(name = "username") String userName) { User user
	 * = userService.findUser(userName) .orElseThrow(() -> new
	 * UserNotFoundException(userName));
	 * 
	 * return new Resource<>(user,
	 * linkTo(methodOn(BookRecommendationRestController.class).getUser(userName)).
	 * withSelfRel()
	 * ,linkTo(methodOn(BookRecommendationRestController.class).getAllUsers()).
	 * withRel("users")); }
	 */

	@GetMapping("v1/users/{username}")
	public User getUser(@PathVariable(name = "username") String userName) {
		return userService.findUser(userName).orElseThrow(() -> new UserNotFoundException(userName));
	}

	/**
	 * Creates a user. Replies to recommendations/{userName} (POST)
	 * 
	 * @return the created entity if any
	 */
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody UserDTO userToCreate) {

		User user = userService.createUser(userToCreate.getName());

		if (user != null) {
			/*URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					// .path("/{id}")
					.buildAndExpand(user.getId()).toUri();*/

			//return ResponseEntity.created(location).build();
			return ResponseEntity.ok(user);
		}

		return ResponseEntity.badRequest().build();
	}
}
