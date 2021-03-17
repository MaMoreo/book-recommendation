package com.zenjob.bookrecommendationservice.service;

import java.util.Collection;

import com.zenjob.bookrecommendationservice.entity.Feedback;
import com.zenjob.bookrecommendationservice.entity.Recommendation;

public interface RecommendationService {

	Recommendation addFeedback(String userName, Long bookId, Feedback fback);

	Recommendation createRecommendation(Recommendation recommendation);

	Collection<Recommendation> findAll();
	
}
