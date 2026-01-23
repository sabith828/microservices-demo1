package com.sabith.loan.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sabith.loan.constants.LoanConstants;
import com.sabith.loan.dto.LoanDTO;
import com.sabith.loan.entity.Loan;
import com.sabith.loan.exception.LoanAlreadyExistsException;
import com.sabith.loan.exception.ResourceNotFoundException;
import com.sabith.loan.mapper.LoanMapper;
import com.sabith.loan.repository.LoanRepository;
import com.sabith.loan.service.LoanService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

	private LoanRepository loanRepository;

	@Override
	@Transactional
	public void createLoan(String mobileNumber) {
		Optional<Loan> optionalLoan = loanRepository.findByMobileNumber(mobileNumber);
		if (optionalLoan.isPresent()) {
			throw new LoanAlreadyExistsException("Loan already registered with given Mobile Number " + mobileNumber);
		}
		loanRepository.save(createNewLoan(mobileNumber));
	}

	@Override
	public LoanDTO fetchLoan(String mobileNumber) {
		Loan loan = loanRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber));
		return LoanMapper.mapToLoanDto(loan, new LoanDTO());
	}

	@Override
	@Transactional
	public void updateLoan(LoanDTO loanDTO) {
		Loan loan = loanRepository.findByLoanNumber(loanDTO.getLoanNumber())
				.orElseThrow(() -> new ResourceNotFoundException("Loan", "Loan Number", loanDTO.getLoanNumber()));
		LoanMapper.mapToLoan(loanDTO, loan);
	}

	@Override
	@Transactional
	public void deleteLoan(String mobileNumber) {
		Loan loan = loanRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber));
		loanRepository.deleteById(loan.getLoanId());
	}

	private Loan createNewLoan(String mobileNumber) {
		Loan newLoan = new Loan();
		long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
		newLoan.setLoanNumber(Long.toString(randomLoanNumber));
		newLoan.setMobileNumber(mobileNumber);
		newLoan.setLoanType(LoanConstants.HOME_LOAN);
		newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
		newLoan.setAmountPaid(0);
		newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
		return newLoan;
	}
}
