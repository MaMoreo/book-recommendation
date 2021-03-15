package com.zenjob.bookrecommendationservice.service;

import org.springframework.stereotype.Service;

import com.zenjob.bookrecommendationservice.entity.Feedback;
import com.zenjob.bookrecommendationservice.entity.Recommendation;
import com.zenjob.bookrecommendationservice.exception.RecommendationNotFoundException;
import com.zenjob.bookrecommendationservice.repository.RecommendationRepository;

@Service
public class RecommendationServiceImpl implements RecommendationService {

	
	private RecommendationRepository recommendationRepository;
	
	public RecommendationServiceImpl(RecommendationRepository recommendationRepository) {
		super();
		this.recommendationRepository = recommendationRepository;
	}

	@Override
	public Recommendation addFeedback(String userName, String bookId, Feedback fback) {
		
		Recommendation recommendation = recommendationRepository
				.findByUserUsernameAndBook(userName, bookId)
				.orElseThrow(() -> new RecommendationNotFoundException(userName, bookId));
		
		recommendation.setFeedback(fback);
		recommendationRepository.save(recommendation);
		return recommendation;
	}

	@Override
	public Recommendation createRecommendation(Recommendation recommendation) {
		return recommendationRepository.save(recommendation);
	}
}
