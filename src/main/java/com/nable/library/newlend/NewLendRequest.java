package com.nable.library.newlend;

import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Range;
import org.springframework.util.Assert;

import com.nable.library.newbook.Book;
import com.nable.library.newbook.Lend;
import com.nable.library.newuser.AskLendWithTime;
import com.nable.library.newuser.User;

//1
public class NewLendRequest implements AskLendWithTime {

	@NotNull
	@Positive
	private Long idUser;

	@NotNull
	@Positive
	private Long idBook;
	// is not mandatory using integer because stays null
	@Range(min = 1, max = 60)
	private Integer time;

	public NewLendRequest(@NotNull Long idUser, @NotNull Long idBook) {
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

	public boolean hasTimeLend() {
		return Optional.ofNullable(time).isPresent();
	}

	public Lend toModel(EntityManager manager) {
		//1
		Book book = manager.find(Book.class, idBook);
		//1
		User user = manager.find(User.class, idUser);
		
		Assert.state(Objects.nonNull(book),"The book need exist");
		Assert.state(Objects.nonNull(user),"The user need exist");
		Assert.state(user.validTimeLend(this),"You are try create a lend with no valid time for this user");
		
		int limitAllocationTime = 60;
		int timeDefined = time == null ? limitAllocationTime : time;
		return  book.createLend(user, timeDefined);
	}
}
