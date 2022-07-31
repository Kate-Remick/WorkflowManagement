package com.nmk.cardinal.services;

import java.util.List;

import com.nmk.cardinal.entities.Card;
import com.nmk.cardinal.entities.Deck;
import com.nmk.cardinal.entities.Workspace;

public interface CardService {

	public List<Card> getCardsByDeck(int deckId, String username);
	
	public List<Card> getCardsAssigned(String username);
	
	public Card createCard(Card newCard, String username);
	
	public boolean deleteCard(int cardId, String username);
	
	public Card editCard(int cardId,Card newCard, String username);
	
	public Card completeCard(Card card, String username);
	
	public Card moveCardToNewDeck(Card card, Deck newDeck, String username);
	
	public Card assignCard(Card card, String username, String managerUsername, Workspace workspace);
	
}
