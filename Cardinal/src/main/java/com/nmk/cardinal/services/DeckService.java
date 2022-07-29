package com.nmk.cardinal.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nmk.cardinal.entities.Card;
import com.nmk.cardinal.entities.Deck;

@Service
public interface DeckService {

	public List<Deck> retrieveDecksByWorkspaceId(int workspaceId);
	
	public Deck createNewDeck(Deck newDeck, String username, int workspaceId);
	
	public boolean destroyDeck(int deckId, String username);
	
	public Deck findById(int deckId, String username);
	
	//This method may be better in card service
	public Deck addCard(int deckId, Card newCard, String username);
	
	
}
