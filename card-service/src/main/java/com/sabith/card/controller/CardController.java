package com.sabith.card.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sabith.card.constants.CardConstants;
import com.sabith.card.dto.CardContactInfoDTO;
import com.sabith.card.dto.CardDTO;
import com.sabith.card.dto.ErrorResponseDTO;
import com.sabith.card.dto.ResponseDTO;
import com.sabith.card.service.CardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@Tag(
        name = "CRUD REST APIs for Card Microservice",
        description = "CRUD REST APIs for Card Microservice to CREATE, UPDATE, FETCH AND DELETE Card details"
)
@RestController
@RequestMapping(path = "/api/card-service/cards", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
public class CardController {
	
	@Value("${build.version}")
    private String buildVersion;
	
	private CardContactInfoDTO cardContactInfoDTO;

	private CardService cardService;
	
	public CardController(CardContactInfoDTO cardContactInfoDTO, CardService cardService) {
		super();
		this.cardContactInfoDTO = cardContactInfoDTO;
		this.cardService = cardService;
	}
	
	@Operation(
            summary = "Create Card REST API",
            description = "REST API to create new Card"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
	@PostMapping
	public ResponseEntity<ResponseDTO> createCard(
			@Valid @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits") String mobileNumber) {
		cardService.createCard(mobileNumber);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDTO(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
	}
	
	@Operation(
            summary = "Fetch Card REST API",
            description = "REST API to fetch Card details based on a Mobile Number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
	@GetMapping
	public ResponseEntity<CardDTO> fetchCard(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits") String mobileNumber) {
		CardDTO cardDTO = cardService.fetchCard(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(cardDTO);
	}
	
	@Operation(
            summary = "Update Card REST API",
            description = "REST API to update Card details based on a Card Number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
	@PutMapping
	public ResponseEntity<ResponseDTO> updateCard(@Valid @RequestBody CardDTO cardDTO) {
		cardService.updateCard(cardDTO);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDTO(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
	}
	
	@Operation(
            summary = "Delete Card REST API",
            description = "REST API to delete Card details based on a Mobile Number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
	@DeleteMapping
	public ResponseEntity<ResponseDTO> deleteCard(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits") String mobileNumber) {
		cardService.deleteCard(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDTO(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
	}
	
	@Operation(
            summary = "Get Build information",
            description = "Get Build information that is deployed into Card Microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(buildVersion);
    }
	
	@Operation(
            summary = "Get Contact Info",
            description = "Contact Info details that can be reached out in case of any issues"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @GetMapping("/contact-info")
    public ResponseEntity<CardContactInfoDTO> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardContactInfoDTO);
    }
}
