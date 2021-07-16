package com.nable.library.newbook;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewBookController {

	@Autowired
	private EntityManager manager;
	
	@PostMapping(value = "/book")
	@Transactional
	public Long execute(@RequestBody @Valid NewBookRequest request) {
		Book newBook = request.toModel();
		manager.persist(newBook);
		return newBook.getId();
	}
}
