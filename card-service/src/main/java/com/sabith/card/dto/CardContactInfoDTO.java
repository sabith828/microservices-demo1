package com.sabith.card.dto;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "card-service")
@Component
@Getter
@Setter
public class CardContactInfoDTO {

	private String message;
	private Map<String, String> contactDetails;
}
