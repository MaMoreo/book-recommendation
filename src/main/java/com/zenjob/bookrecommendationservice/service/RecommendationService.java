package com.zenjob.bookrecommendationservice.service;

import com.zenjob.bookrecommendationservice.entity.Feedback;
import com.zenjob.bookrecommendationservice.entity.Recommendation;

public interface RecommendationService {

	Recommendation addFeedback(String userName, String bookId, Feedback fback);

	Recommendation createRecommendation(Recommendation recommendation);
	
}
