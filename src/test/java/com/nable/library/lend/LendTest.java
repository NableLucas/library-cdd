package com.nable.library.lend;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.nable.library.newbook.Book;
import com.nable.library.newbook.Lend;
import com.nable.library.newinstance.Instance;
import com.nable.library.newinstance.Type;
import com.nable.library.newuser.User;
import com.nable.library.newuser.UserType;

public class LendTest {

	@Test
	@DisplayName("should be verify lend is not expired")
	void test1() throws Exception {
		User user = new User(UserType.RESSEARCH);
		Book book = new Book("title", BigDecimal.TEN, "1239485930");
		Instance instance = new Instance(Type.FREE, book);
		int time = 10;
		Lend lend = new Lend(user, instance, time);
		Assertions.assertFalse(lend.expired(Clock.systemDefaultZone()));
	}
	
	@ParameterizedTest
	@CsvSource({
		"10",
		"11"
	})
	@DisplayName("verify the expiration")
	void test2(int time) throws Exception {
		User user = new User(UserType.RESSEARCH);
		Book book = new Book("title", BigDecimal.TEN, "1239485930");
		Instance instance = new Instance(Type.FREE, book);
		
		Lend lend = new Lend(user, instance, time);
		Clock clockFixed = Clock.fixed(Instant.now().plus(time+1, ChronoUnit.DAYS), ZoneOffset.systemDefault());
		Assertions.assertTrue(lend.expired(clockFixed));
	}
}

