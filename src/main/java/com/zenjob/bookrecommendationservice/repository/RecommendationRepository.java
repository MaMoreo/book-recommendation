package com.zenjob.bookrecommendationservice.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zenjob.bookrecommendationservice.entity.Recommendation;

/**
 * CRUD operations for Recommendations
 */
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

	/**
	 * Finder method that dereferences the username property 
	 * on the Bookmark entity�s Account relationship, 
	 * ultimately requiring a join of some sort. 
	 * 
	 * The JPA query it generates is, roughly: 
	 * SELECT b from Bookmark b WHERE b.account.username = :username.
	 * 
	 * @param username username in Account to be found
	 * @return A collection with all the accouts for this user.
	 */
	Collection<Recommendation> findByUserUsername(String username);
	
	Optional<Recommendation> findByUserUsernameAndBook(String username, String asin);
}
