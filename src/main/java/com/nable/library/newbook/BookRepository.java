package com.nable.library.newbook;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends org.springframework.data.repository.Repository<Book, Long> {

	Optional<Book> findByIsbn(String isbn);

}
