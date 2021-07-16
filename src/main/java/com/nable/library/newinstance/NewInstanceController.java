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

@RestController
public class NewInstanceController {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private EntityManager manager;

	@PostMapping(value = "/book/{isbn}/instances")
	@Transactional
	public ResponseEntity<?> execute(@PathVariable("isbn") String isbn, @RequestBody @Valid NewInstanceRequest request) {
		Optional<Book> possibleBook = repository.findByIsbn(isbn);
		if(possibleBook.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Instance newInstance = request.toModel(possibleBook.get());
		manager.persist(newInstance);
		
		return ResponseEntity.ok(newInstance.getId());
	}

}
