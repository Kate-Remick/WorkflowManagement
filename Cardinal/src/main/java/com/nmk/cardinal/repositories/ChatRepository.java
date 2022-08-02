package com.nmk.cardinal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.nmk.cardinal.entities.Chat;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

	public List<Chat> findByUsers_UsernameEquals(@Param("username") String username);
	
	public List<Chat> findByUsers_UsernameEqualsAndWorkspace_IdEquals(@Param("username") String username, @Param("id") int id);
 }
