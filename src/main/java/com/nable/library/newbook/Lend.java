package com.nable.library.newbook;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import com.nable.library.newinstance.Instance;
import com.nable.library.newuser.User;

@Entity
public class Lend {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private @NotNull @Valid User user;
	@ManyToOne
	private Instance instanceSelected;
	private @Positive Integer time;
	private Instant instantReturned;
	
	
	@Deprecated
	public Lend() {
		super();
	}

	public Lend(@NotNull @Valid User user, Instance instanceSelected, 
			@Positive Integer time) {
		Assert.isTrue(instanceSelected.accept(user),"You are creating a lend with instance not accept the user, maybe you verify correctly?");
		
		this.user = user;
		this.instanceSelected = instanceSelected;
		this.time = time;
	}
	
	public boolean expired() {
		return this.instantReturned
				.plus(time, ChronoUnit.DAYS)
				.compareTo(Instant.now()) < 0; // when is less then 0 the data is expired
	}

	public Long getId() {
		Assert.state(Objects.nonNull(id), "Maybe you forget to persist lend?");
		return id;
	}

	public boolean wasReturned() {
		return Objects.nonNull(instantReturned);
	}

}
