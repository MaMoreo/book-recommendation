package com.zenjob.bookrecommendationservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zenjob.bookrecommendationservice.entity.Feedback;
import com.zenjob.bookrecommendationservice.entity.Recommendation;
import com.zenjob.bookrecommendationservice.service.RecommendationService;

@RestController
@RequestMapping("recommendations")
public class RecommendationRestController {

	private final RecommendationService recommendationService;

	public RecommendationRestController(RecommendationService recommendationService) {
		super();
		this.recommendationService = recommendationService;
	}

	/**
	 * recommendations/{userName}/{bookId}
	 * 
	 * @param userName
	 * @param bookId
	 * @param fback
	 * @return
	 */
	@PutMapping("/{userName}/{bookId}")
	public ResponseEntity<Recommendation> giveFeedback(@RequestParam String userName, @RequestParam String bookId,
			@RequestBody Feedback fback) {

		return ResponseEntity.ok(recommendationService.addFeedback(userName, bookId, fback));
	}
}
