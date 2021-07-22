package com.nable.library.newuser;

public enum UserType {
	DEFAULT {
		@Override
		boolean accetvalidTimeLend(AskLendWithTime ask) {
			return ask.hasTimeLend();
		}
	},
	RESSEARCH {
		@Override
		boolean accetvalidTimeLend(AskLendWithTime ask) {
			return true;
		}
	};

	abstract boolean accetvalidTimeLend(AskLendWithTime ask);
}
