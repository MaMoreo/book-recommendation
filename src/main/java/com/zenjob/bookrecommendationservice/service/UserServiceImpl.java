package com.zenjob.bookrecommendationservice.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenjob.bookrecommendationservice.entity.User;
import com.zenjob.bookrecommendationservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	@Override
	public Collection<User> getAllUsers() {
		return userRepository.findAll();
	}

}
