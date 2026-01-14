package com.sabith.account.service;

import com.sabith.account.dto.CustomerDTO;

public interface AccountService {

	void createAccount(CustomerDTO customerDTO);

	CustomerDTO fetchAccount(String mobileNumber);

	void updateAccount(CustomerDTO customerDTO);

	void deleteAccount(String mobileNumber);
}
