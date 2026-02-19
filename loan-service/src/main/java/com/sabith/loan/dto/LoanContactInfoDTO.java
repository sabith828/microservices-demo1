package com.sabith.loan.dto;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "loan-service")
@Component
@Getter
@Setter
public class LoanContactInfoDTO {

	private String message;
	private Map<String, String> contactDetails;
}
