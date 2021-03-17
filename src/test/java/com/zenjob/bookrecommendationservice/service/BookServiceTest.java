package com.zenjob.bookrecommendationservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zenjob.bookrecommendationservice.entity.Book;
import com.zenjob.bookrecommendationservice.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	@InjectMocks
	BookServiceImpl bookService;
	
	@Mock
	private BookRepository bookRepository;

	private List<Book> storedBooks;
	private int total;
	
	
	@BeforeEach
	void setUp() throws Exception {
		bookService = new BookServiceImpl (bookRepository);
	
		Book b1 = Book.builder()
				.id(0L)
				.asin(100L)
				.title("No vas a aprender en tu puta vida")
				.author("Med Vega")
				.genre("Sci-Fi")
				.build();
		Book b2 = Book.builder().id(1L).asin(200L).title("2001: A space Odissey").build();
		Book b3 = Book.builder().id(30L).asin(300L).title("Tarzan").build();
		Book b4 = Book.builder().id(402L).asin(400L).title("King Kong").build();
		storedBooks = new ArrayList<>();
		storedBooks.add(b1);
		storedBooks.add(b2);
		storedBooks.add(b3);
		storedBooks.add(b4);
		
		total = storedBooks.size();
	}

	@Test
	void testGetAllBooks() {
		
		when(bookRepository.findAll()).thenReturn(storedBooks);
		assertEquals(total,  bookService.getAllBooks().size());
	}

	@Test
	void testGet_N_Books() {
		
		when(bookRepository.findAll()).thenReturn(storedBooks);
		assertEquals(2, bookService.getNBooks(2).size());
	}
	
	
	@Test
	void testGet_N_Books_N_BiggerThanSize() {
		
		when(bookRepository.findAll()).thenReturn(storedBooks);
		// asked for 5 books but we only have 2
		assertEquals(total, bookService.getNBooks(5).size());
	}
	
	@Test
	void testGet_N_Books_CheckDuplicates() {
		
		Book b1_copy = Book.builder()
				.id(10L)  // Excluded from Equals
				.asin(100L)
				.title("No vas a aprender en tu puta vida")
				.author("Med Vega")
				.genre("Sci-Fi")
				.build();
		storedBooks.add(b1_copy);
		total = storedBooks.size();
		
		when(bookRepository.findAll()).thenReturn(storedBooks);
		
		// asked for 10 books
		// total is 5 but one is duplicated 
		assertEquals(total - 1, bookService.getNBooks(10).size());
	}
}
