package com.nable.library.newinstance;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nable.library.newbook.Book;
import com.nable.library.newbook.BookRepository;

//total 5
@RestController
public class NewInstanceController {

	// 1
	@Autowired
	private BookRepository repository;

	@Autowired
	private EntityManager manager;

	@PostMapping(value = "/book/{isbn}/instances")
	@Transactional
	// 1 newinstance request
	public ResponseEntity<?> execute(@PathVariable("isbn") String isbn,
			@RequestBody @Valid NewInstanceRequest request) {
		// 1
		Optional<Book> possibleBook = repository.findByIsbn(isbn);

		// 1
		return possibleBook.map(book -> {
			//Instance newInstance = request.toModel(possibleBook.get());
			Instance newInstance = book.newInstance(request.getType());
			manager.persist(newInstance);
			
			return ResponseEntity.ok(newInstance.getId());
		}).orElse(ResponseEntity.notFound().build());

	}

}
