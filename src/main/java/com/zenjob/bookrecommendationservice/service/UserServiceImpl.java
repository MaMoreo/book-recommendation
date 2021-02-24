package com.zenjob.bookrecommendationservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

}
