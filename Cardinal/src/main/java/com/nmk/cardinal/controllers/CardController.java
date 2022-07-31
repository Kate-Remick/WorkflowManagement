package com.nmk.cardinal.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	private List<Card> getCardsInDeck(@PathVariable("id") int id, HttpServletResponse res, Principal principal){
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
	
	

}
