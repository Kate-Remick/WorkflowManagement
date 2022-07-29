package com.nmk.cardinal.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nmk.cardinal.entities.Chat;
import com.nmk.cardinal.entities.Workspace;

@Service
public interface ChatService {

	public Chat createChat(Chat newChat, String username, Workspace workspace, List<String> users);
	
	
	
	
}
