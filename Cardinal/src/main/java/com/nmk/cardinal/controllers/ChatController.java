package com.nmk.cardinal.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nmk.cardinal.entities.Chat;
import com.nmk.cardinal.entities.User;
import com.nmk.cardinal.entities.Workspace;
import com.nmk.cardinal.services.ChatService;

@CrossOrigin({"*", "http://localhost:4200"})
@RestController
@RequestMapping("api")
public class ChatController {

	@Autowired
	private ChatService chatServ;
	
	@GetMapping("chats/workspace/{id}")
	public List<Chat> getChatsInWorkspace(@PathVariable int id, Principal principal, HttpServletResponse res){
		List<Chat> chats = null;
		try {
			chats = chatServ.getWorkspaceChats(principal.getName(), id);
			
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return chats;
	}
	
	
	@GetMapping("chats")
	public List<Chat> getChats(Principal principal, HttpServletResponse res){
		List<Chat> chats = null;
		try {
			chats = chatServ.getUserChats(principal.getName());
			
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return chats;
	}
	
	
	@PostMapping("chats/workspace/{id}")
	public Chat createChat(Principal principal, @RequestBody Chat chat, HttpServletResponse res, @PathVariable int id) {
		Chat newChat = null;
		try {
			newChat = chatServ.createChat(newChat, principal.getName() , id);
			if(newChat != null) {
				res.setStatus(201);
			}
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return newChat;
	}
	
	@PutMapping("chats/leave")
	public void leaveChat(HttpServletResponse res, Principal principal, @RequestBody Chat chat) {
		try {
			chatServ.leaveChat(chat, principal.getName());
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
	@PutMapping("chats/{id}/addUsers")
	public Chat addUsers(HttpServletResponse res, Principal principal, @RequestBody Chat chat, @PathVariable int id) {
		try {
			chat = chatServ.addUsers(chat, principal.getName(), id);
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		
		return chat;
	}
	
	
//	@PutMapping("chats/addUser/{username}")
//	public Chat addUser(HttpServletResponse res, Principal principal, @RequestBody Chat chat, @PathVariable String username) {
//		try {
//			chat = chatServ.addUser(chat, username, principal.getName());
//		}catch(Exception e) {
//			e.printStackTrace();
//			res.setStatus(400);
//		}
//		
//		return chat;
//	}
	
	
	
}
