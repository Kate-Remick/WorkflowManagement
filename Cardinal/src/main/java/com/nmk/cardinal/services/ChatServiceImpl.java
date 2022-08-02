package com.nmk.cardinal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmk.cardinal.entities.Chat;
import com.nmk.cardinal.entities.User;
import com.nmk.cardinal.entities.Workspace;
import com.nmk.cardinal.repositories.ChatRepository;
import com.nmk.cardinal.repositories.UserRepository;
import com.nmk.cardinal.repositories.WorkspaceRepository;

@Service
public class ChatServiceImpl  implements ChatService{

	@Autowired 
	private ChatRepository chatRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private WorkspaceRepository workRepo;
	
	@Override
	public Chat createChat(Chat newChat, String username, int workspaceId) {
		User user = userRepo.findByUsername(username);
		Workspace workspace = null;
		List<User> chatUsers = new ArrayList<>();
		chatUsers.add(user);
		Optional<Workspace> op = workRepo.findById(workspaceId);
		if(op.isPresent()) {
			workspace = op.get();
		}
		if(workspace != null && workspace.getUsers().contains(user)) {
			chatUsers.addAll(newChat.getUsers());
			
			newChat.setUsers(chatUsers);
			newChat.setWorkspace(workspace);
			newChat = chatRepo.saveAndFlush(newChat);
		}
		
		
		return newChat;
	}

	@Override
	public Chat addUsers(Chat newChat, String username, int chatId) {
		User user = userRepo.findByUsername(username);
		Chat chat = null;
		Optional<Chat> op = chatRepo.findById(chatId);
		if(op.isPresent()) {
			chat = op.get();
		}		
		List<User> inChat = chat.getUsers();
		if(inChat.contains(user)) {
			inChat.addAll(newChat.getUsers());
			chat.setUsers(inChat);
			chat = chatRepo.saveAndFlush(chat);
		}
		return chat;
	}

//	@Override
//	public Chat addUser(Chat chat, String newUsername, String username) {
//		User user = userRepo.findByUsername(username);
//		List<User> inChat = chat.getUsers();
//		if(inChat.contains(user)) {
//			User addedUser = userRepo.findByUsername(newUsername);
//			if(addedUser != null) {
//				inChat.add(user);
//			}
//			chat.setUsers(inChat);
//		}
//		chat = chatRepo.saveAndFlush(chat);
//		return chat;
//	}

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

	@Override
	public List<Chat> getUserChats(String username) {
		return chatRepo.findByUsers_UsernameEquals(username);
	}

	@Override
	public List<Chat> getWorkspaceChats(String username, int workspaceId) {
		return chatRepo.findByUsers_UsernameEqualsAndWorkspace_IdEquals(username, workspaceId);
	}

	
	
}
