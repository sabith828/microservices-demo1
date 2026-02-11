package com.sabith.account.dto;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "account-service")
@Component
@Getter
@Setter
public class AccountContactInfoDTO {

	private String message;
	private Map<String, String> contactDetails;
}
