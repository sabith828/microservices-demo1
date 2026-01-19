package com.sabith.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Account", description = "Schema to hold Account information")
public class AccountDTO {

	@NotEmpty(message = "AccountNumber can not be a null or empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "AccountNumber must be 10 digits")
	@Schema(description = "Account Number of Bank Account", example = "2154433243")
	private Long accountNumber;

	@NotEmpty(message = "AccountType can not be a null or empty")
	@Schema(description = "Account Type of Bank Account", example = "Savings")
	private String accountType;

	@NotEmpty(message = "BranchAddress can not be a null or empty")
	@Schema(description = "Bank Branch Address", example = "123 Colombo")
	private String branchAddress;
}
