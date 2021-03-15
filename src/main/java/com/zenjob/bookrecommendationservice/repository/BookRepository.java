package com.zenjob.bookrecommendationservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.zenjob.bookrecommendationservice.entity.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
