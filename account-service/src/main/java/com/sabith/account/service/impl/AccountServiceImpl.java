package com.sabith.account.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sabith.account.constants.AccountConstants;
import com.sabith.account.dto.AccountDTO;
import com.sabith.account.dto.CustomerDTO;
import com.sabith.account.entity.Account;
import com.sabith.account.entity.Customer;
import com.sabith.account.exception.CustomerAlreadyExistsException;
import com.sabith.account.exception.ResourceNotFoundException;
import com.sabith.account.mapper.AccountMapper;
import com.sabith.account.mapper.CustomerMapper;
import com.sabith.account.repository.AccountRepository;
import com.sabith.account.repository.CustomerRepository;
import com.sabith.account.service.AccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;
	private CustomerRepository customerRepository;

	@Override
	@Transactional
	public void createAccount(CustomerDTO customerDTO) {
		Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
		Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());

		if (optionalCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException(
					"Customer already registered with given Mobile Number " + customerDTO.getMobileNumber());
		}
		Customer savedCustomer = customerRepository.save(customer);
		accountRepository.save(createNewAccount(savedCustomer));
	}

	@Override
	public CustomerDTO fetchAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

		Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

		CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
		customerDTO.setAccountDTO(AccountMapper.mapToAccountDTO(account, new AccountDTO()));
		return customerDTO;
	}

	@Override
	@Transactional
	public void updateAccount(CustomerDTO customerDTO) {
		AccountDTO accountDTO = customerDTO.getAccountDTO();
		Account account = accountRepository.findById(accountDTO.getAccountNumber())
				.orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber",
						accountDTO.getAccountNumber().toString()));

		AccountMapper.mapToAccount(accountDTO, account);

		Long customerId = account.getCustomerId();
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString()));

		CustomerMapper.mapToCustomer(customerDTO, customer);

	}

	@Override
	@Transactional
	public void deleteAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		accountRepository.deleteByCustomerId(customer.getCustomerId());
		customerRepository.deleteById(customer.getCustomerId());
	}

	private Account createNewAccount(Customer customer) {
		Account newAccount = new Account();
		newAccount.setCustomerId(customer.getCustomerId());
		long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);

		newAccount.setAccountNumber(randomAccountNumber);
		newAccount.setAccountType(AccountConstants.SAVINGS);
		newAccount.setBranchAddress(AccountConstants.ADDRESS);
		return newAccount;
	}
}
