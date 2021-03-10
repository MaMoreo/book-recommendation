package com.zenjob.bookrecommendationservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zenjob.bookrecommendationservice.model.CSVFileReader;

@Configuration
public class Config {

	@Bean
	CSVFileReader csvFileReaderCreator() {
		return new CSVFileReader();
	}
	
}
