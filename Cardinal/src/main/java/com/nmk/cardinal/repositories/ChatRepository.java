package com.nmk.cardinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nmk.cardinal.entities.Chat;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

}
