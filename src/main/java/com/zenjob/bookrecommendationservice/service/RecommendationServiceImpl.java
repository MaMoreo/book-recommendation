package com.zenjob.bookrecommendationservice.service;

import java.util.Collection;

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
	public Recommendation addFeedback(String userName, Long bookId, Feedback fback) {
		
		Recommendation recommendation = recommendationRepository
				.findByUserUsernameAndBookId(userName, bookId)
				.orElseThrow(() -> new RecommendationNotFoundException(userName, bookId));
		
		recommendation.setFeedback(fback);
		return recommendationRepository.save(recommendation);
	}

	@Override
	public Recommendation createRecommendation(Recommendation recommendation) {
		return recommendationRepository.save(recommendation);
	}

	@Override
	public Collection<Recommendation> findAll() {
		return (Collection<Recommendation>) recommendationRepository.findAll();
	}
}
