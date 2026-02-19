package com.sabith.loan.controller;

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

import com.sabith.loan.constants.LoanConstants;
import com.sabith.loan.dto.ErrorResponseDTO;
import com.sabith.loan.dto.LoanContactInfoDTO;
import com.sabith.loan.dto.LoanDTO;
import com.sabith.loan.dto.ResponseDTO;
import com.sabith.loan.service.LoanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@Tag(
        name = "CRUD REST APIs for Loan Microservice",
        description = "CRUD REST APIs for Loan Microservice to CREATE, UPDATE, FETCH AND DELETE Loan details"
)
@RestController
@RequestMapping(path = "/api/loan-service/loans", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
public class LoanController {
	
	@Value("${build.version}")
    private String buildVersion;
	
	private LoanContactInfoDTO loanContactInfoDTO;

	private LoanService loanService;
	
	public LoanController(LoanContactInfoDTO loanContactInfoDTO, LoanService loanService) {
		super();
		this.loanContactInfoDTO = loanContactInfoDTO;
		this.loanService = loanService;
	}
	
	@Operation(
            summary = "Create Loan REST API",
            description = "REST API to create new Loan"
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
	public ResponseEntity<ResponseDTO> createLoan(
			@Valid @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits") String mobileNumber) {
		loanService.createLoan(mobileNumber);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDTO(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
	}
	
	@Operation(
            summary = "Fetch Loan REST API",
            description = "REST API to fetch Loan details based on a Mobile Number"
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
	public ResponseEntity<LoanDTO> fetchCard(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits") String mobileNumber) {
		LoanDTO loanDTO = loanService.fetchLoan(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(loanDTO);
	}
	
	@Operation(
            summary = "Update Loan REST API",
            description = "REST API to update Loan details based on a Loan Number"
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
	public ResponseEntity<ResponseDTO> updateLoan(@Valid @RequestBody LoanDTO loanDTO) {
		loanService.updateLoan(loanDTO);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDTO(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
	}
	
	@Operation(
            summary = "Delete Loan REST API",
            description = "REST API to delete Loan details based on a Mobile Number"
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
	public ResponseEntity<ResponseDTO> deleteLoan(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits") String mobileNumber) {
		loanService.deleteLoan(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDTO(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
	}
	
	@Operation(
            summary = "Get Build information",
            description = "Get Build information that is deployed into Loan Microservice"
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
    public ResponseEntity<LoanContactInfoDTO> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loanContactInfoDTO);
    }
}
