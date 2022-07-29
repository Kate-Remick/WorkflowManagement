package com.nmk.cardinal.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nmk.cardinal.entities.Card;
import com.nmk.cardinal.entities.Deck;

@Service
public interface CardService {

	public List<Card> getCardsByDeck(int deckId, String username);
	
	public List<Card> getCardsAssigned(String username);
	
	public Card createCard(Card newCard, String username);
	
	public boolean deleteCard(Card card, String username);
	
	public Card editCard(Card currentCard, Card newCard, String username);
	
	public Card completeCard(Card card, String username);
	
	public Card moveCardToNewDeck(Card card, Deck newDeck, String username);
	
	public Card assignCard(Card card, String username, String managerUsername);
	
}
