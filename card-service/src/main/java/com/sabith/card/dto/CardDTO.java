package com.sabith.card.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(name = "Card", description = "Schema to hold Card information")
@Data
public class CardDTO {

	@NotEmpty(message = "Mobile Number can not be a null or empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
	@Schema(description = "Mobile Number of the Customer", example = "1845432123")
	private String mobileNumber;

	@NotEmpty(message = "Card Number can not be a null or empty")
	@Pattern(regexp = "(^$|[0-9]{12})", message = "Card Number must be 12 digits")
	@Schema(description = "Card Number of the Customer", example = "215443324311")
	private String cardNumber;

	@NotEmpty(message = "Card Type can not be a null or empty")
	@Schema(description = "Type of the Card", example = "Credit Card")
	private String cardType;

	@Positive(message = "Total Card Limit should be greater than zero")
	@Schema(description = "Total Amount Limit available against a Card", example = "100000")
	private int totalLimit;

	@PositiveOrZero(message = "Total Amount used should be equal or greater than zero")
	@Schema(description = "Total Amount used by a Customer", example = "1000")
	private int amountUsed;

	@PositiveOrZero(message = "Total Available Amount should be equal or greater than zero")
	@Schema(description = "Total Available Amount against a Card", example = "90000")
	private int availableAmount;

}
