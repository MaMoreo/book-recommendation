package com.zenjob.bookrecommendationservice.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This Class reads the CVS file
 * 
 * @author Miguel Moreo
 */
public class CSVFileReader {

	private static final String SEMICOLON = ";";
	private final static Logger LOGGER = Logger.getLogger(CSVFileReader.class.getName());
	private static FileHandler fileHandler;

	/**
	 * Constructor
	 */
	/*public CSVFileReader() {
		// init();
	}*/

	/**
	 * Initializes the Logger.
	 */
	private void init() {
		try {
			fileHandler = new FileHandler(".\\logs\\out.log");  // FIXME: does not work
			LOGGER.addHandler(fileHandler);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		LOGGER.setUseParentHandlers(false);
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
			books = lines//.sorted()  //FIXME: this is  NOT true, how this works?
					.skip(1) //first one is the headers line
					.map(mapToBook)
					.collect(Collectors.toList());
		} catch (IOException ioe) {
			// We log the error in the line, but continue with the processing.
			LOGGER.severe("IOException occurred when reading the file " + filePath + " exiting");
		}
		return books;
	}

	/**
	 * Builds a {@link Book} out of a CSV line.
	 */
	private Function<String, Book> mapToBook = (line) -> {
		String[] p = line.split(SEMICOLON);// CSV has semicolon separated lines
		Book book = new Book()
				.withAsin(p[0])   //		book.setAsIN(Integer.parseInt(p[0]));
				.withTitle(p[1])
				.withAuthor(p[2])
				.withGenre(p[3]);
		return book;
	};
}
