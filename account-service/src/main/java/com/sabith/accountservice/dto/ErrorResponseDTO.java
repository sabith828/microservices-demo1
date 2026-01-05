package com.sabith.accountservice.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "ErrorResponse", description = "Schema to hold Error Response information")
public class ErrorResponseDTO {

	@Schema(description = "API path invoked by client")
	private String apiPath;

	@Schema(description = "Error Code representing the error happened")
	private HttpStatus errorCode;

	@Schema(description = "Error Message representing the error happened")
	private String errorMessage;

	@Schema(description = "Time representing when the error happened")
	private LocalDateTime errorTime;
}
