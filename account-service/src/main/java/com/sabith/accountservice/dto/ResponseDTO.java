package com.sabith.accountservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "Response", description = "Schema to hold successful Response information")
@Data
@AllArgsConstructor
public class ResponseDTO {

	@Schema(description = "Status Code in the response")
	private String statusCode;

	@Schema(description = "Status Message in the response")
	private String statusMessage;
}
