package com.nable.library.newuser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private @NotNull UserType type;

	public User(@NotNull UserType type) {
		this.type = type;
	}
	
	public Long getId() {
		Assert.state(id!=null,"ID don't be null at this points, did you call save(method)?");
		return id;
	}
	
}
