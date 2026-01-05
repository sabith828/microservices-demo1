package com.sabith.accountservice.mapper;

import com.sabith.accountservice.dto.CustomerDTO;
import com.sabith.accountservice.entity.Customer;

public class CustomerMapper {

	public static CustomerDTO mapToCustomerDto(Customer customer, CustomerDTO customerDTO) {
		customerDTO.setName(customer.getName());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setMobileNumber(customer.getMobileNumber());
		return customerDTO;
	}

	public static Customer mapToCustomer(CustomerDTO customerDTO, Customer customer) {
		customer.setName(customerDTO.getName());
		customer.setEmail(customerDTO.getEmail());
		customer.setMobileNumber(customerDTO.getMobileNumber());
		return customer;
	}
}
