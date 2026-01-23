package com.sabith.loan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(name = "Loan", description = "Schema to hold Loan information")
@Data
public class LoanDTO {

	@NotEmpty(message = "Mobile Number can not be a null or empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
	@Schema(description = "Mobile Number of the Customer", example = "1845432123")
	private String mobileNumber;

	@NotEmpty(message = "Loan Number can not be a null or empty")
	@Pattern(regexp = "(^$|[0-9]{12})", message = "Loan Number must be 12 digits")
	@Schema(description = "Loan Number of the Customer", example = "215443324312")
	private String loanNumber;

	@NotEmpty(message = "Loan Type can not be a null or empty")
	@Schema(description = "Type of the Loan", example = "Home Loan")
	private String loanType;

	@Positive(message = "Total Loan Amount should be greater than zero")
	@Schema(description = "Total Loan Amount", example = "100000")
	private int totalLoan;

	@PositiveOrZero(message = "Total Loan Amount paid should be equal or greater than zero")
	@Schema(description = "Total Loan Amount paid", example = "1000")
	private int amountPaid;

	@PositiveOrZero(message = "Total Outstanding Amount should be equal or greater than zero")
	@Schema(description = "Total Outstanding Amount against a Loan", example = "99000")
	private int outstandingAmount;

}
