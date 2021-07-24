package com.nable.library.newinstance;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.nable.library.newbook.Book;
import com.nable.library.newlend.ValidateBookForLend;
import com.nable.library.newuser.User;
import com.nable.library.newuser.UserType;

public class ValidateBookForLendTest {
		
	@DisplayName("Should interrupt the validation if existis error before")
	@Test
	void teste1() throws Exception {
		ValidateBookForLend validator = new ValidateBookForLend();
		
		Book book = new Book("title", BigDecimal.TEN, "1293847367");
		book.newInstance(Type.RESTRICT);
		
		User user = new User(UserType.DEFAULT);
		
		Errors errors = Mockito.spy(new BeanPropertyBindingResult(new Object(), "target"));
		
		validator.validate(user, book, errors);
		
		Assertions.assertEquals(1, errors.getErrorCount());
		Mockito.verify(errors,Mockito.never()).reject("This book is not disponible for lend");		
	}
	
//	@DisplayName("Reject the lend if the book is not disponible")
//	@Test
//	void teste2() throws Exception {
//		ValidateBookForLend validator = new ValidateBookForLend();
//		
//		Book book = new Book("title", BigDecimal.TEN, "1293847367");
//		book.newInstance(Type.FREE);
//		
//		User user = new User(UserType.DEFAULT);
//		
//		ReflectionTestUtils.setField(user, "id", 1l);
//		
//		user.
//		
//		Errors errors = Mockito.spy(new BeanPropertyBindingResult(new Object(), "target"));
//		
//		validator.validate(user, book, errors);
//		
//		Assertions.assertEquals(1, errors.getErrorCount());
//		Mockito.verify(errors,Mockito.never()).reject("This book is not disponible for lend");		
//	}

}
