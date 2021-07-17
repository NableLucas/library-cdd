package com.nable.library.newuser;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

public class NewUserRequest {
	
	@NotNull
	private UserType type;

	@JsonCreator(mode = Mode.PROPERTIES)
	public NewUserRequest(@NotNull UserType type) {
		this.type = type;
	}

	public User toModel() {
		return new User(type);
	}
	
	
}
