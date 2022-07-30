package com.nmk.cardinal.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nmk.cardinal.entities.Chat;
import com.nmk.cardinal.entities.User;
import com.nmk.cardinal.entities.Workspace;
import com.nmk.cardinal.repositories.ChatRepository;
import com.nmk.cardinal.repositories.UserRepository;

public class ChatServiceImpl  implements ChatService{

	@Autowired 
	private ChatRepository chatRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Chat createChat(Chat newChat, String username, Workspace workspace, List<String> users) {
		User user = userRepo.findByUsername(username);
		List<User> chatUsers = new ArrayList<>();
		chatUsers.add(user);
		if(workspace.getUsers().contains(user)) {
			users.add(username);
			for (String un : users) {
				User addedUser = userRepo.findByUsername(un);
				if(addedUser != null) {
					chatUsers.add(addedUser);
				}
			}
			newChat.setUsers(chatUsers);
			newChat.setWorkspace(workspace);
			newChat = chatRepo.saveAndFlush(newChat);
		}
		
		
		return newChat;
	}

	@Override
	public Chat addUsers(Chat chat, List<String> users, String username) {
		User user = userRepo.findByUsername(username);
		List<User> inChat = chat.getUsers();
		if(inChat.contains(user)) {
			for (String un : users) {
				User addedUser = userRepo.findByUsername(un);
				if(addedUser != null) {
					inChat.add(user);
				}
			}
			chat.setUsers(inChat);
		}
		chat = chatRepo.saveAndFlush(chat);
		return chat;
	}

	@Override
	public Chat addUser(Chat chat, String newUsername, String username) {
		User user = userRepo.findByUsername(username);
		List<User> inChat = chat.getUsers();
		if(inChat.contains(user)) {
			User addedUser = userRepo.findByUsername(newUsername);
			if(addedUser != null) {
				inChat.add(user);
			}
			chat.setUsers(inChat);
		}
		chat = chatRepo.saveAndFlush(chat);
		return chat;
	}

	@Override
	public void leaveChat(Chat chat, String username) {
		User user = userRepo.findByUsername(username);
		List<User> inChat = chat.getUsers();
		if(inChat.contains(user)) {
			inChat.remove(user);
		}
		chat.setUsers(inChat);
		chat = chatRepo.saveAndFlush(chat);
		
	}

	
	
}
