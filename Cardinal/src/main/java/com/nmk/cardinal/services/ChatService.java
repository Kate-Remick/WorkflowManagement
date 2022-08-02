package com.nmk.cardinal.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nmk.cardinal.entities.Chat;
import com.nmk.cardinal.entities.Workspace;


public interface ChatService {

//	public Chat createChat(Chat newChat, String username, Workspace workspace, List<String> users);
	public Chat createChat(Chat newChat, String username, int workspaceId);
	
//	public Chat addUsers(Chat chat, List<String> users, String username);
	public Chat addUsers(Chat chat, String username, int chatId);
	
//	public Chat addUser(Chat chat, String newUsername, String username);
	
	public void leaveChat(Chat chat, String username);
	
	public List<Chat> getUserChats(String username);
	
	public List<Chat> getWorkspaceChats(String username, int workspaceId);
	
	
	
}
