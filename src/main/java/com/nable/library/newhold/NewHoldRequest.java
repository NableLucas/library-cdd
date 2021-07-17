package com.nable.library.newhold;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Range;

public class NewHoldRequest {
	
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
	
	

}
