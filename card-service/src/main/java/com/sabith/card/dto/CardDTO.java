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

	@NotEmpty(message = "MobileNumber can not be a null or empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "MobileNumber must be 10 digits")
	@Schema(description = "Mobile Number of Customer", example = "1845432123")
	private String mobileNumber;

	@NotEmpty(message = "CardNumber can not be a null or empty")
	@Pattern(regexp = "(^$|[0-9]{12})", message = "CardNumber must be 12 digits")
	@Schema(description = "Card Number of the Customer", example = "215443324311")
	private String cardNumber;

	@NotEmpty(message = "CardType can not be a null or empty")
	@Schema(description = "Type of the Card", example = "Credit Card")
	private String cardType;

	@Positive(message = "Total Card limit should be greater than zero")
	@Schema(description = "Total amount limit available against a Card", example = "100000")
	private int totalLimit;

	@PositiveOrZero(message = "Total amount used should be equal or greater than zero")
	@Schema(description = "Total amount used by a Customer", example = "1000")
	private int amountUsed;

	@PositiveOrZero(message = "Total available amount should be equal or greater than zero")
	@Schema(description = "Total available amount against a card", example = "90000")
	private int availableAmount;

}
