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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nmk.cardinal.entities.Deck;
import com.nmk.cardinal.services.DeckService;

@CrossOrigin({"*", "http://localhost:4200"})
@RestController
@RequestMapping("api")
public class DeckController {
	
	@Autowired
	private DeckService deckServ;
	
	
	@GetMapping("decks/workspace/{id}")
	public List<Deck> getWorkspaceDecks(@PathVariable int id, HttpServletResponse res, Principal principal ){
		List<Deck> decks = null;
		try {
			decks = deckServ.retrieveDecksByWorkspaceId(id);
		}catch(Exception e){
			res.setStatus(400);
			e.printStackTrace();
		}
		return decks;
	}
	
	@GetMapping("decks/{id}")
	public Deck getDeckById(@PathVariable int id, HttpServletResponse res, Principal principal) {
		Deck deck = null;
		try {
			deck = deckServ.findById(id, principal.getName());
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return deck;
	}
	
	@PostMapping("decks/workspace/{id}")
	public Deck createDeck(@PathVariable int id, @RequestBody Deck deck, Principal principal, HttpServletResponse res) {
		try {
			deck = deckServ.createNewDeck(deck, principal.getName(), id);
			res.setStatus(201);
		}catch(Exception e){
			e.printStackTrace();
			res.setStatus(400);
		}
		return deck;
	}
	
	@DeleteMapping("decks/{id}")
	public void destroyDeck(@PathVariable int id, Principal principal, HttpServletResponse res) {
		try {
			boolean destroyed = deckServ.destroyDeck(id, principal.getName());
			if(destroyed) {
				res.setStatus(204);
			}
		}catch(Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
	}
	
	
	@PutMapping("decks/{id}")
	public Deck editDeck(@PathVariable int id, Principal principal, HttpServletResponse res, @RequestBody Deck newDeck) {
		Deck deck = null;
		try {
			deck = deckServ.editDeck(id, principal.getName(), newDeck);
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return deck;
	}
	


}
