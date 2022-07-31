package com.nmk.cardinal.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmk.cardinal.entities.Card;
import com.nmk.cardinal.entities.Deck;
import com.nmk.cardinal.entities.User;
import com.nmk.cardinal.entities.Workspace;
import com.nmk.cardinal.repositories.CardRepository;
import com.nmk.cardinal.repositories.UserRepository;



@Service
public class CardServiceImpl implements CardService{

	@Autowired
	private CardRepository cardRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<Card> getCardsByDeck(int deckId, String username) {
		List<Card> cards = null;
		User user = userRepo.findByUsername(username);
		if(user != null) {
			cards = cardRepo.findByDeck_IdEquals(deckId);
		}
		return cards;
	}

	@Override
	public List<Card> getCardsAssigned(String username) {
		User user = userRepo.findByUsername(username);
		return cardRepo.findByAssignedUserEquals(user);
	}

	@Override
	public Card createCard(Card newCard, String username) {
		return cardRepo.saveAndFlush(newCard);
	}

	@Override
	public boolean deleteCard(Card card, String username) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			cardRepo.delete(card);
		}
		return !cardRepo.equals(card);
	}

	@Override
	public Card editCard(Card currentCard, Card newCard, String username) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			currentCard.setName(newCard.getName());
			currentCard.setDueDate(newCard.getDueDate());
			currentCard.setDescription(newCard.getDescription());
			currentCard = cardRepo.saveAndFlush(currentCard);
		}
		
		return currentCard;
	}

	@Override
	public Card completeCard(Card card, String username) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			card.setCompleted(true);
			card.setCompletedAt(LocalDateTime.now());
			card = cardRepo.saveAndFlush(card);
		}
		
		return card;
	}

	@Override
	public Card moveCardToNewDeck(Card card, Deck newDeck, String username) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			card.setDeck(newDeck);
			card = cardRepo.saveAndFlush(card);
		}
		
		
		return card;
	}

	@Override
	public Card assignCard(Card card, String username, String managerUsername, Workspace workspace) {
		User manager = userRepo.findByUsername(managerUsername);
		if(workspace.getManager().equals(manager)) {
			User user = userRepo.findByUsername(username);
			if(user != null) {
				card.setAssignedUser(user);
				card = cardRepo.saveAndFlush(card);
			}
		}
		
		
		return card;
	}
	
	

}
