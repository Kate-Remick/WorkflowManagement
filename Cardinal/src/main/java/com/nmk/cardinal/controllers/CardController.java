package com.nmk.cardinal.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nmk.cardinal.entities.Card;
import com.nmk.cardinal.services.AuthService;
import com.nmk.cardinal.services.CardService;

@CrossOrigin({ "*", "http://localhost:4200" })
@RestController
public class CardController {
	
	@Autowired
	private CardService cardServ;
	
	@Autowired
	private AuthService Auth;
	
	
	@GetMapping("cards/deck/{id}")
	public  List<Card> getCardsInDeck(@PathVariable("id") int id, HttpServletResponse res, Principal principal){
		List<Card> cards = null;
		try {
			cards = cardServ.getCardsByDeck(id, principal.getName());
			res.setStatus(200);
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return cards;
	}
	
	@GetMapping("cards/assigned")
	public List<Card> getAssigned( HttpServletResponse res, Principal principal){
		List<Card> cards = null;
		try {
			cards = cardServ.getCardsAssigned(principal.getName());
			res.setStatus(200);
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return cards;
		
	}
		
	@PostMapping("cards")
	public Card create(HttpServletResponse res, Principal principal, @RequestBody Card card) {
		try {
			card = cardServ.createCard(card, principal.getName());
			
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return card;
		
	}
	
	@DeleteMapping("cards/{id}")
	public void deleteCard(HttpServletResponse res, @PathVariable int id, Principal principal) {
		try {
			boolean deleted = cardServ.deleteCard(id, principal.getName());
			if(deleted) {
				res.setStatus(204);
			}
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
	@PutMapping("cards/edit/{id}")
	public Card editCard(HttpServletResponse res, @RequestBody Card newCard, Principal principal, @PathVariable int id) {
		Card card = null;
		try {
			card = cardServ.editCard(id, newCard, principal.getName());
			if(card == null) {
				res.setStatus(500);
			}
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return card;
	}
	
	
	

}
