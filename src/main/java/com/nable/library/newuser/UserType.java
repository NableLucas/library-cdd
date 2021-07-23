package com.nable.library.newuser;

public enum UserType {
	DEFAULT {
		@Override
		boolean accetvalidTimeLend(AskLendWithTime ask) {
			return ask.hasTimeLend();
		}

		@Override
		boolean acceptNewLend(long quantityLendsNoReturned) {
			return quantityLendsNoReturned < 5;
		}
	},
	RESSEARCH {
		@Override
		boolean accetvalidTimeLend(AskLendWithTime ask) {
			return true;
		}

		@Override
		boolean acceptNewLend(long quantityLendsNoReturned) {
			return true;
		}
	};

	abstract boolean accetvalidTimeLend(AskLendWithTime ask);

	abstract boolean acceptNewLend(long quantityLendsNoReturned);
}
