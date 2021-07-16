package com.nable.library.newinstance;

import javax.validation.constraints.NotNull;

import com.nable.library.newbook.Book;

public class NewInstanceRequest {

	@NotNull
	private Type type;

	public void setType(Type type) {
		this.type = type;
	}

	public Instance toModel(Book book) {
		return new Instance(type, book);
	}

}
