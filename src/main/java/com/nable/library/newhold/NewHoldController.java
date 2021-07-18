package com.nable.library.newhold;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nable.library.newbook.Hold;

@RestController
public class NewHoldController {
	
	@Autowired	
	private EntityManager manager;
	
	@Autowired
	private BasicVerificationHoldValidator basicVerificationHoldValidator;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(basicVerificationHoldValidator);
	}
	
	@PostMapping(value = "/api/hold")
	@Transactional
	public Long executa(@RequestBody @Valid NewHoldRequest request){
		Hold newHold = request.toModel(manager);
		manager.persist(newHold);
		return newHold.getId();
	}
}

