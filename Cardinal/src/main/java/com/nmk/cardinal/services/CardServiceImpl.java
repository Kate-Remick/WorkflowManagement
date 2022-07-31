package com.nmk.cardinal.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nmk.cardinal.entities.Card;
import com.nmk.cardinal.entities.Deck;



@Service
public class CardServiceImpl implements CardService{

	@Override
	public List<Card> getCardsByDeck(int deckId, String username) {
		List<Card> cards = null;
		
		return ;
	}

	@Override
	public List<Card> getCardsAssigned(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card createCard(Card newCard, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCard(Card card, String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Card editCard(Card currentCard, Card newCard, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card completeCard(Card card, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card moveCardToNewDeck(Card card, Deck newDeck, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card assignCard(Card card, String username, String managerUsername) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
