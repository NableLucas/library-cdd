package com.nable.library.newinstance;

import com.nable.library.newuser.User;
import com.nable.library.newuser.UserType;

public enum Type {

	FREE {
		@Override
		boolean accept(User user) {
			return true;
		}
	},RESTRICT {
		@Override
		boolean accept(User user) {
			return user.type(UserType.RESSEARCH);
		}
	};

	abstract boolean accept(User user);
}
