package com.nmk.cardinal.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nmk.cardinal.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

	public List<Message> findByChatId_OrderByCreatedAt(@Param("chatId") int chatId);
	
	@Query("Select m FROM message m where m.chat.id = :chatId AND where m.createdAt GREATER THAN :date")
	public List<Message> findByChatAndSince(@Param("chatId") int chatId, @Param("date") LocalDateTime date);
	
	@Query("Select m FROM message m where m.chat.id = :chatId AND where m.createdAt = :date")
	public List<Message> findByChatAndDate(@Param("chatId") int chatId, @Param("date") LocalDateTime date);
	
	
}
