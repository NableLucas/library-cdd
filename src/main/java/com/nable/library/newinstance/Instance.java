package com.nable.library.newinstance;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import com.nable.library.newbook.Book;
import com.nable.library.newbook.Hold;
import com.nable.library.newuser.User;

@Entity
public class Instance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotNull Type type;
	
	//1
	@ManyToOne
	private @NotNull @Valid Book book;
	//1
	@OneToMany(mappedBy = "instanceSelected")
	private List<Hold> holds = new ArrayList<Hold>();
	
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

	public boolean disponibleForHold() {
		//1
		return this.holds.isEmpty()
				||
				this.holds.stream().allMatch(hold -> hold.wasReturned());
	}

	public boolean disponible(@NotNull @Valid User user) {
		return this.accept(user) && this.disponibleForHold();
	}
	
	
	
	

}
