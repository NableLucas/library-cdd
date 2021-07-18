package com.nable.library.newhold;

import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Range;
import org.springframework.util.Assert;

import com.nable.library.newbook.Book;
import com.nable.library.newbook.Hold;
import com.nable.library.newuser.AskHoldWithTime;
import com.nable.library.newuser.User;

//1
public class NewHoldRequest implements AskHoldWithTime {

	@NotNull
	@Positive
	private Long idUser;

	@NotNull
	@Positive
	private Long idBook;
	// is not mandatory using integer because stays null
	@Range(min = 1, max = 60)
	private Integer time;

	public NewHoldRequest(@NotNull Long idUser, @NotNull Long idBook) {
		super();
		this.idUser = idUser;
		this.idBook = idBook;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Long getIdUser() {
		return idUser;
	}

	public Long getIdBook() {
		return idBook;
	}

	public boolean hasTimeHold() {
		return Optional.ofNullable(time).isPresent();
	}

	public Hold toModel(EntityManager manager) {
		//1
		Book book = manager.find(Book.class, idBook);
		//1
		User user = manager.find(User.class, idUser);
		
		Assert.state(Objects.nonNull(book),"The book need exist");
		Assert.state(Objects.nonNull(user),"The user need exist");
		Assert.state(user.validTimeHold(this),"You are try create a hold with no valid time for this user");
		
		int limitAllocationTime = 60;
		int timeDefined = time == null ? limitAllocationTime : time;
		return  book.createHold(user, timeDefined);
	}
}
