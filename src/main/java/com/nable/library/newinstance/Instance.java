package com.nable.library.newinstance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import com.nable.library.newbook.Book;
import com.nable.library.newuser.User;

@Entity
public class Instance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Type type;
	
	@ManyToOne
	@NotNull
	@Valid
	private Book book;
	
	@Deprecated
	public Instance() {
	}

	public Instance(@NotNull Type type,@NotNull @Valid Book book) {
		this.type = type;
		this.book = book;
	}
	
	public Long getId() {
		Assert.state(id!=null,"Id is null, call persist?");
		return id;
	}

	public boolean accept(User user) {
		return this.type.accept(user);
	}
	
	
	
	

}
