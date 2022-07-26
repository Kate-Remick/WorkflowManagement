package com.nmk.cardinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nmk.cardinal.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
