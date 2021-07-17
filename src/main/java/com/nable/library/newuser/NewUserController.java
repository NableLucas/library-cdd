package com.nable.library.newuser;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewUserController {
	
	@Autowired
	private EntityManager manager;
	
	@PostMapping(value = "/api/users")
	@Transactional
	public Long executa(@RequestBody @Valid NewUserRequest request){
		User newUser = request.toModel();
		manager.persist(newUser);
		return newUser.getId();
	}
}

