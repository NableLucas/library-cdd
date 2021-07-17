package com.nable.library.newhold;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nable.library.newbook.Book;
import com.nable.library.newuser.User;

//2 points total
@Component
public class BasicVerificationHoldValidator implements Validator {

	private EntityManager manager;

	public BasicVerificationHoldValidator(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return NewHoldRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// 1
		if (errors.hasErrors()) {
			return;
		}
		// 1
		NewHoldRequest request = (NewHoldRequest) target;
		// 1
		User user = manager.find(User.class, request.getIdUser());
		// 1
		Book book = manager.find(Book.class, request.getIdBook());

		Assert.state(user != null, "User can not be null to validate");
		Assert.state(book != null, "Book can not be null to validate");

		// 1
		// validation if the book accept be hold by user
		if (!book.acceptBeHoldFor(user)) {
			errors.reject(null, "This user can't take this book");
		}

		//1
		// validation if the user need to set the hold time
		if (!user.validTimeHold(request)) {
			errors.reject(null, "You need define hold time");
		}

	}

}
