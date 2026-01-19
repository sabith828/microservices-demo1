package com.sabith.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sabith.account.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByMobileNumber(String mobileNumber);
}
