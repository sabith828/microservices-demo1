package com.sabith.loan.service;

import com.sabith.loan.dto.LoanDTO;

public interface LoanService {

	void createLoan(String mobileNumber);

	LoanDTO fetchLoan(String mobileNumber);

	void updateLoan(LoanDTO loanDTO);

	void deleteLoan(String mobileNumber);
}
