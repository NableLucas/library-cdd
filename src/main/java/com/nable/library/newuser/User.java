package com.nable.library.newuser;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import com.nable.library.newbook.Hold;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Deprecated
	public User() {
	}
	private @NotNull UserType type;
	
	@OneToMany(mappedBy =  "user")
	private List<Hold> holds = new ArrayList<>();

	public User(@NotNull UserType type) {
		this.type = type;
	}
	
	public Long getId() {
		Assert.state(id!=null,"ID don't be null at this points, did you call save(method)?");
		return id;
	}

	public boolean type(UserType typeSearched) {
		return this.type.equals(typeSearched);
	}

	//1
	public boolean validTimeHold(AskHoldWithTime ask) {
		return type.accetvalidTimeHold(ask);
	}

	public boolean canAskForHold() {
		long quantityHoldsNoReturned = this.holds.stream()
				.filter(hold -> !hold.wasReturned())
				.count();
		int limitHold = 5;
		return quantityHoldsNoReturned < limitHold;
	}

	
}
