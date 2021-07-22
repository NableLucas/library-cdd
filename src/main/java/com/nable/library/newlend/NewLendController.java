package com.nable.library.newlend;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nable.library.newbook.Lend;

@RestController
public class NewLendController {
	
	@Autowired	
	private EntityManager manager;
	
	@Autowired
	private BasicVerificationLendValidator basicVerificationLendValidator;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(basicVerificationLendValidator);
	}
	
	@PostMapping(value = "/api/lend")
	@Transactional
	public Long executa(@RequestBody @Valid NewLendRequest request){
		Lend newLend = request.toModel(manager);
		manager.persist(newLend);
		return newLend.getId();
	}
}

