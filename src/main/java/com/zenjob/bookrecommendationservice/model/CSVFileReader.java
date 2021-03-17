package com.zenjob.bookrecommendationservice.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.zenjob.bookrecommendationservice.entity.Book;

import lombok.extern.slf4j.Slf4j;

/**
 * Reads the CVS file
 * 
 * @author Miguel Moreo
 */
@Slf4j
@Configuration
public class CSVFileReader {

	private static final String SEMICOLON = ";";
	@Value("${books.filepath:src/main/resources/data/books.csv}") // Spring Language Expression
	private String FILE_PATH; 

	public List<Book> processBooks() {
		return processBooks(FILE_PATH);
	}
	
	/**
	 * Reads the books file. A CSV File containing the books
	 * with the following info: ANSI, Title, Author, Genre.
	 * 
	 * @param filePath The path to the "books" file.
	 * @return A list with all the books, sorted alphabetically.<br>
	 *         An {@code Empty List} is the file does not exist.
	 */
	public List<Book> processBooks(String filePath) {
		List<Book> books = new ArrayList<>();
		try (Stream<String> lines = Files.lines(Paths.get(filePath))) {   //reads the whole file
			books = lines
					.skip(1) //first one is the headers line
					.map(mapToBook)
					.collect(Collectors.toList());
		} catch (IOException ioe) {
			// We log the error in the line, but continue with the processing.
			log.error("IOException occurred when reading the file " + filePath + " exiting");
		}
		return books;
	}

	/**
	 * Builds a {@link Book} out of a CSV line.
	 */
	private Function<String, Book> mapToBook = (line) -> {
		String[] p = line.split(SEMICOLON); // CSV has semicolon separated lines
		return  Book.builder()
				.asin(Long.parseLong(p[0]))
				.title(p[1])
				.author(p[2])
				.genre(p[3])
				.build();
	};
}
