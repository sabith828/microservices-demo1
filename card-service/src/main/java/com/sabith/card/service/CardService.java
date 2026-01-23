package com.sabith.card.service;

import com.sabith.card.dto.CardDTO;

public interface CardService {

	void createCard(String mobileNumber);

	CardDTO fetchCard(String mobileNumber);

	void updateCard(CardDTO cardDTO);

	void deleteCard(String mobileNumber);
}
