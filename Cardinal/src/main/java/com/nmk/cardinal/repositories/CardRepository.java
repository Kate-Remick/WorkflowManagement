package com.nmk.cardinal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.nmk.cardinal.entities.Card;
import com.nmk.cardinal.entities.User;

public interface CardRepository extends JpaRepository<Card, Integer> {

	public List<Card> findByDeck_IdEquals(@Param("id") int id);
	
	public List<Card> findByAssignedUserEquals(@Param("assignedUser") User assingnedUSer);
	
	
}
