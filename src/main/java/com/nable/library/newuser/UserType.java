package com.nable.library.newuser;

public enum UserType {
	DEFAULT {
		@Override
		boolean accetvalidTimeHold(AskHoldWithTime ask) {
			return ask.hasTimeHold();
		}
	},
	RESSEARCH {
		@Override
		boolean accetvalidTimeHold(AskHoldWithTime ask) {
			return true;
		}
	};

	abstract boolean accetvalidTimeHold(AskHoldWithTime ask);
}
