package com.zenjob.bookrecommendationservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zenjob.bookrecommendationservice.entity.User;

/**
 * CRUD Operations for User Entity.
 * 
 * @author Miguel Moreo
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Custom finder-method. It creates a JPA query of the form "select a from
	 * Account a where a.username = :username," run it (passing in the method
	 * argument username as a named parameter for the query), and return the
	 * results for us.
	 * 
	 * @param username
	 *            the username to match
	 * @return The account if found.
	 */
	Optional<User> findByUsername(String username);
}
