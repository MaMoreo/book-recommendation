package com.zenjob.bookrecommendationservice.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zenjob.bookrecommendationservice.entity.Book;
import com.zenjob.bookrecommendationservice.service.BookService;

@RestController
@RequestMapping("recommendations")
public class BookRestController {

	private final BookService bookService;

	public BookRestController(BookService bookService) {
		super();
		this.bookService = bookService;
	}

	@GetMapping("/books")
	public Collection<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
}
