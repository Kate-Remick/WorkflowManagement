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
	
	//mailman is map that contains the object types as a list of string keys - if the object it's mapped to is a workspace,
	//the string should be called workspace. This map allows the mailan to carry multiple of different kinds of objects to the 
	//method for use. In this case, chat is a chat, workspace is a workspace and usernames is a list of strings
	
	@PostMapping("chats")
	public Chat createChat(Principal principal, @RequestBody Map<String, Object> mailman, HttpServletResponse res) {
		Chat newChat = null;
		try {
			newChat = chatServ.createChat((Chat)mailman.get("chat"), principal.getName(), (Workspace)mailman.get("workspace"), (List<String>)mailman.get("usernames"));
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
	
	@PutMapping("chats/addUsers")
	public Chat addUsers(HttpServletResponse res, Principal principal, @RequestBody Map<String, Object> mailman) {
		Chat chat = null;
		try {
			chat = chatServ.addUsers((Chat)mailman.get("chat"), (List<String>)mailman.get("usernames"), principal.getName());
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		
		return chat;
	}
	
	
	@PutMapping("chats/addUser/{username}")
	public Chat addUser(HttpServletResponse res, Principal principal, @RequestBody Chat chat, @PathVariable String username) {
		try {
			chat = chatServ.addUser(chat, username, principal.getName());
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		
		return chat;
	}
	
	
	
}
