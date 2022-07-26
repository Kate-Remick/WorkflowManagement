package com.nmk.cardinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nmk.cardinal.entities.Deck;

public interface DeckRepository extends JpaRepository<Deck, Integer> {

}
