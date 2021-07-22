package com.nable.library.newlend;

import org.springframework.validation.Errors;

import com.nable.library.newbook.Book;
import com.nable.library.newuser.User;

//2
public class ValidateBookForLend {

		public void validate(User user, Book book, Errors errors) {
			// 1
			if (!book.acceptBeLendFor(user)) {
				errors.reject(null, "This user can't take this book");
			}
			//1
			if(!book.isDisponibilityForLend()) {
				errors.reject(null, "This book is not disponible for lend");
			}
		
	}

}
