package com.nable.library.newhold;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NewHoldRequest {
	
	@NotNull
	@Positive
	private Long idUser;
	
	@NotNull
	@Positive
	private Long idBook;
	// is not mandatory using integer because stays null
	private Integer time;
	
	public NewHoldRequest(@NotNull Long idUser, @NotNull Long idBook) {
		super();
		this.idUser = idUser;
		this.idBook = idBook;
	}
	
	public void setTime(Integer time) {
		this.time = time;
	}
	
	

}
