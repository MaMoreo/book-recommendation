package com.zenjob.bookrecommendationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookRecommendationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookRecommendationServiceApplication.class, args);
	}
	
	
	/**
	 * Once started, Spring Boot will call all beans of type CommandLineRunner,
	 * giving them a callback.
	 * 
	 * In this case, CommandLineRunner is an interface with one abstract method,
	 * which means that - in the world of Java 8 - we can substitute its
	 * definition with a lambda expression. (the lambda syntax its like an
	 * anonymous inner class implementing the interface in question).
	 * 
	 * @param userRepository
	 *            Spring will inject this automatically
	 * @param recommedationRepository
	 *            Spring will inject this automatically
	 * @return
	 */
	/*@Bean
	CommandLineRunner init(UserRepository userRepository, RecommendationRepository recommedationRepository) {
		// CommandLineRunner runner = (String [] evt) -> // body of the run method
				// parameter of the run method -> body of the run method
				return (evt) -> 
				
						Arrays.asList("steve,peter,bruce, clark, felicia, maryjane, diana, eddie".replaceAll("\\s", "").split(","))
						.forEach(a -> {
							User user = userRepository.save(new User(a));
							//recommedationRepository.save(new Recommendation(user, "http://bookmark.com/1/" + a, "A description"));
							//recommedationRepository.save(new Recommendation(user, "http://bookmark.com/2/" + a, "A description"));
						}
						);
	}*/
}
