package com.nmk.cardinal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmk.cardinal.entities.Card;
import com.nmk.cardinal.entities.Deck;
import com.nmk.cardinal.entities.User;
import com.nmk.cardinal.entities.Workspace;
import com.nmk.cardinal.repositories.CardRepository;
import com.nmk.cardinal.repositories.DeckRepository;
import com.nmk.cardinal.repositories.UserRepository;
import com.nmk.cardinal.repositories.WorkspaceRepository;

@Service
public class DeckServiceImpl implements DeckService{

	@Autowired
	private DeckRepository deckRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private WorkspaceRepository workRepo;
	
	@Autowired 
	private CardRepository cardRepo;
	
	
	@Override
	public List<Deck> retrieveDecksByWorkspaceId(int workspaceId) {
		Optional<Workspace> op = workRepo.findById(workspaceId);
		List<Deck> workspaceDecks = new ArrayList<>();
		if(op.isPresent()) {
			Workspace workspace = op.get();
			workspaceDecks = workspace.getDecks();
		}
		return workspaceDecks;
	}

	@Override
	public Deck createNewDeck(Deck newDeck, String username, int workspaceId) {
		User user = userRepo.findByUsername(username);
		Optional<Workspace> op = workRepo.findById(workspaceId);
		if(op.isPresent()) {
			Workspace workspace = op.get();
			newDeck.setWorkspace(workspace);
			if(workspace.getUsers().contains(user)) {
				newDeck = deckRepo.saveAndFlush(newDeck);
			}
		}
		
		return newDeck;
	}

	@Override
	public boolean destroyDeck(int deckId, String username) {
		Deck deck = null;
		Optional<Deck> op = deckRepo.findById(deckId);
		if(op.isPresent()) {
			deck = op.get();
			for (Card card : deck.getCards()) {
				cardRepo.delete(card);
			}
			deckRepo.delete(deck);
		}
		
		return !deckRepo.existsById(deckId);
	}

	@Override
	public Deck findById(int deckId, String username) {
		Deck deck = null;
		User user = userRepo.findByUsername(username);
		if(user != null) {
			Optional<Deck> op = deckRepo.findById(deckId);
			if(op.isPresent()) {
				deck = op.get();
			}
		}
		return deck;
	}

	@Override
	public Deck addCard(int deckId, Card newCard, String username) {
		User user = userRepo.findByUsername(username);
		Deck deck = null;
		if(user != null) {
			Optional<Deck> op = deckRepo.findById(deckId);
			if(op.isPresent()) {
				deck = op.get();
				newCard.setDeck(deck);
				newCard = cardRepo.saveAndFlush(newCard);
				List<Card> cards = deck.getCards();
				cards.add(newCard);
				deck.setCards(cards);
				deck = deckRepo.saveAndFlush(deck);
			}
		}
		return deck;
	}

	@Override
	public Deck editDeck(int deckId, String username, Deck newDeck) {
		User user = userRepo.findByUsername(username);
		Deck deck = null;
		//Edit for permissions?
		if(user != null) {
			Optional<Deck> op = deckRepo.findById(deckId);
			if(op.isPresent()) {
				deck = op.get();
				deck.setDescription(newDeck.getDescription());
				deck.setName(newDeck.getName());
				deck = deckRepo.saveAndFlush(deck);
			}
		}
		return deck;
	}

	
	
	
}
