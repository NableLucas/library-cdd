package com.nable.library.newbook;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.ISBN.Type;
import org.springframework.util.Assert;

import com.nable.library.newinstance.Instance;
import com.nable.library.newuser.User;

//6 points total
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String title;
	@NotNull
	@Positive
	private BigDecimal price;
	@NotBlank
	@ISBN(type = Type.ISBN_10)
	private String isbn;
	// 1
	@OneToMany(mappedBy = "book")
	private List<Instance> instances = new ArrayList<>();

	public Book() {

	}

	public Book(@NotBlank String title, @NotNull @Positive BigDecimal price,
			@NotBlank @ISBN(type = Type.ISBN_10) String isbn) {
		super();
		this.title = title;
		this.price = price;
		this.isbn = isbn;
	}

	public Long getId() {
		Assert.state(id != null, "Get id don't work with null");
		return this.id;
	}

	// 1
	public boolean acceptBeHoldFor(User user) {
		// 1
		return instances.stream().anyMatch(instance -> instance.accept(user));
	}

	public Hold createHold(@NotNull @Valid User user, @Positive Integer time) {
		Assert.isTrue(this.acceptBeHoldFor(user), "You try hold book is not possible for this user");
		
		//1
		Instance instanceSelected = instances.stream()
				.filter(instance -> instance.accept(user))
				.findFirst().get();
		
		Assert.isTrue(instanceSelected.disponibleForHold(),"Your code don't try create a hold for indisponible instance");
		
		//1
		return new Hold(user, instanceSelected, time);
	}

	public boolean isDisponibilityForHold() {
		//1
		return instances.stream().anyMatch(instance -> instance.disponibleForHold());
	}
}
