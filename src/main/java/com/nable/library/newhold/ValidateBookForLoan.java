package com.nable.library.newhold;

import org.springframework.validation.Errors;

import com.nable.library.newbook.Book;
import com.nable.library.newuser.User;

//2
public class ValidateBookForLoan {

		public void validate(User user, Book book, Errors errors) {
			// 1
			if (!book.acceptBeHoldFor(user)) {
				errors.reject(null, "This user can't take this book");
			}
			//1
			if(!book.isDisponibilityForHold()) {
				errors.reject(null, "This book is not disponible for hold");
			}
		
	}

}
