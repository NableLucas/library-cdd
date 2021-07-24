package com.nable.library.newlend;

import java.time.Clock;
import java.util.Objects;

import javax.persistence.EntityManager;

import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nable.library.newuser.User;

public class LendExpireValidator implements Validator {
	
	private EntityManager manager;
	private Clock clock;
	
	
	public LendExpireValidator(EntityManager manager, Clock clock) {
		super();
		this.manager = manager;
		this.clock = clock;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return NewLendRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		NewLendRequest request = (NewLendRequest) target;
		User user = manager.find(User.class, request.getIdUser());
		
		Assert.state(Objects.nonNull(user), "User need to exist");
		
		if(user.limitExpiredLends(clock)) {
			errors.reject(null, "You have limit expired lend");
		}
		
	}

}
