package com.nmk.cardinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nmk.cardinal.entities.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {

}
