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

import com.nable.library.newbook.Lend;

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
	private List<Lend> lends = new ArrayList<>();

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
	public boolean validTimeLend(AskLendWithTime ask) {
		return type.accetvalidTimeLend(ask);
	}

	public boolean canAskForLend() {
		long quantityLendsNoReturned = this.lends.stream()
				.filter(lend -> !lend.wasReturned())
				.count();
		int limitLend = 5;
		return quantityLendsNoReturned < limitLend;
	}

	
}
