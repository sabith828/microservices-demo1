package com.sabith.account.controller;

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

import com.sabith.account.constants.AccountConstants;
import com.sabith.account.dto.AccountContactInfoDTO;
import com.sabith.account.dto.CustomerDTO;
import com.sabith.account.dto.ErrorResponseDTO;
import com.sabith.account.dto.ResponseDTO;
import com.sabith.account.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@Tag(
        name = "CRUD REST APIs for Account Microservice",
        description = "CRUD REST APIs for Account Microservice to CREATE, UPDATE, FETCH AND DELETE Account details"
)
@RestController
@RequestMapping(path = "/api/account-service/accounts", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
public class AccountController {
	
	@Value("${build.version}")
    private String buildVersion;
	
	private AccountContactInfoDTO accountContactInfoDTO;

	private AccountService accountService;
	
	
	public AccountController(AccountService accountService, AccountContactInfoDTO accountContactInfoDTO) {
		this.accountService = accountService;
		this.accountContactInfoDTO = accountContactInfoDTO;
	}

	@Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer and Account"
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
	public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
		accountService.createAccount(customerDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDTO(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
	}
	
	@Operation(
            summary = "Fetch Account REST API",
            description = "REST API to fetch Customer and Account details based on a Mobile Number"
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
	public ResponseEntity<CustomerDTO> fetchAccount(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits") String mobileNumber) {
		CustomerDTO customerDTO = accountService.fetchAccount(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
	}
	
	@Operation(
            summary = "Update Account REST API",
            description = "REST API to update Customer and Account details based on a Account Number"
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
	public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerDTO customerDTO) {
		accountService.updateAccount(customerDTO);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDTO(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
	}
	
	@Operation(
            summary = "Delete Account REST API",
            description = "REST API to delete Customer and Account details based on a Mobile Number"
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
	public ResponseEntity<ResponseDTO> deleteAccount(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits") String mobileNumber) {
		accountService.deleteAccount(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDTO(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
	}
	
	@Operation(
            summary = "Get Build information",
            description = "Get Build information that is deployed into Account Microservice"
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
    public ResponseEntity<AccountContactInfoDTO> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountContactInfoDTO);
    }
}
