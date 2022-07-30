package com.nmk.cardinal.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.nmk.cardinal.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

	//Not 100% on these queries - could take some work
	public List<Message> findByChat_IdEqualsSortByMessage_CreatedAt(@Param("chatId") int chatId);
	
	//@Query(need to make this work)
//	public List<Message> findByChatSortByDateGreaterThan(@Param("chatId") int chatId, @Param("createdAt") LocalDateTime createdAt);
	
	
	
}
