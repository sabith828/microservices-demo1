package com.sabith.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema to hold Customer and Account information")
public class CustomerDTO {

	@Schema(description = "Name of the Customer", example = "Sabith")
	@NotEmpty(message = "Name can not be a null or empty")
	@Size(min = 5, max = 30, message = "The length of the Customer Name should be between 5 and 30")
	private String name;

	@Schema(description = "Email Address of the Customer", example = "sabith@gmail.com")
	@NotEmpty(message = "Email Address can not be a null or empty")
	@Email(message = "Email Address should be a valid value")
	private String email;

	@Schema(description = "Mobile Number of the Customer", example = "1845432123")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
	private String mobileNumber;

	@Schema(description = "Account details of the Customer")
	private AccountDTO accountDTO;
}
