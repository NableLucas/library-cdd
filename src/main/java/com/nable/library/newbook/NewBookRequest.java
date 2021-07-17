package com.nable.library.newbook;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.ISBN.Type;

import com.nable.library.shared.UniqueValue;

public class NewBookRequest {

	@NotBlank
	private String title;
	@NotNull
	@Positive
	private BigDecimal price;
	@NotBlank
	@ISBN(type = Type.ISBN_10)
	@UniqueValue(domainClass = Book.class, fieldName = "isbn")
	private String isbn;

	public NewBookRequest(@NotBlank String title, @NotNull @Positive BigDecimal price,
			@NotBlank @ISBN(type = Type.ISBN_10) String isbn) {
		super();
		this.title = title;
		this.price = price;
		this.isbn = isbn;
	}

	public Book toModel() {
		return new Book(title, price, isbn);
	}

}
