package com.sabith.card.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sabith.card.constants.CardConstants;
import com.sabith.card.dto.CardDTO;
import com.sabith.card.entity.Card;
import com.sabith.card.exception.CardAlreadyExistsException;
import com.sabith.card.exception.ResourceNotFoundException;
import com.sabith.card.mapper.CardMapper;
import com.sabith.card.repository.CardRepository;
import com.sabith.card.service.CardService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

	private CardRepository cardRepository;

	@Override
	@Transactional
	public void createCard(String mobileNumber) {
		Optional<Card> optionalCard = cardRepository.findByMobileNumber(mobileNumber);
		if (optionalCard.isPresent()) {
			throw new CardAlreadyExistsException("Card already registered with given mobileNumber " + mobileNumber);
		}
		cardRepository.save(createNewCard(mobileNumber));
	}

	@Override
	public CardDTO fetchCard(String mobileNumber) {
		Card card = cardRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
		return CardMapper.mapToCardDTO(card, new CardDTO());
	}

	@Override
	@Transactional
	public void updateCard(CardDTO cardDTO) {
		Card card = cardRepository.findByCardNumber(cardDTO.getCardNumber())
				.orElseThrow(() -> new ResourceNotFoundException("Card", "CardNumber", cardDTO.getCardNumber()));
		CardMapper.mapToCard(cardDTO, card);
	}

	@Override
	@Transactional
	public void deleteCard(String mobileNumber) {
		Card card = cardRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
		cardRepository.deleteById(card.getCardId());
	}

	private Card createNewCard(String mobileNumber) {
		Card newCard = new Card();
		long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
		newCard.setCardNumber(Long.toString(randomCardNumber));
		newCard.setMobileNumber(mobileNumber);
		newCard.setCardType(CardConstants.CREDIT_CARD);
		newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
		newCard.setAmountUsed(0);
		newCard.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
		return newCard;
	}
}
