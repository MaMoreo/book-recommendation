package com.zenjob.bookrecommendationservice.service;

import java.util.Collection;
import java.util.Optional;

import com.zenjob.bookrecommendationservice.entity.User;

public interface UserService {

	Collection<User> getAllUsers();
	Optional<User> findUser(String userName);
	User createUser(String userName);
}
