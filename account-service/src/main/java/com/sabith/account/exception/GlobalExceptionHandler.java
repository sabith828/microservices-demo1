package com.sabith.account.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sabith.account.dto.ErrorResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDTO> handleCustomerAlreadyExistsException(
			CustomerAlreadyExistsException exception, WebRequest webRequest) {

		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(webRequest.getDescription(false),
				HttpStatus.BAD_REQUEST, exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
	}
}
