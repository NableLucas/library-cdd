package com.nable.library.newlend;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nable.library.newbook.Book;
import com.nable.library.newuser.User;

//7
@Component
public class BasicVerificationLendValidator implements Validator {

	private EntityManager manager;

	public BasicVerificationLendValidator(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return NewLendRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// 1
		if (errors.hasErrors()) {
			return;
		}
		// 1
		NewLendRequest request = (NewLendRequest) target;
		// 1
		User user = manager.find(User.class, request.getIdUser());
		// 1
		Book book = manager.find(Book.class, request.getIdBook());

		Assert.state(user != null, "User can not be null to validate");
		Assert.state(book != null, "Book can not be null to validate");
		//1
		new ValidateBookForLend().validate(user, book, errors);
		
		//1
		if (!user.validTimeLend(request)) {
			errors.reject(null, "You need define lend time");
		}
		
		if(!user.canAskForLend()) {
			errors.reject(null, "You are on limit for lend, limit is 5");
		}
		
		
		

	}

}
