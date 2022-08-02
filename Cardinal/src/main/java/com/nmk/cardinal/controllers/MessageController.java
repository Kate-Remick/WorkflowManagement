package com.nmk.cardinal.controllers;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nmk.cardinal.entities.Message;
import com.nmk.cardinal.services.MessageService;

@CrossOrigin({"*", "http://localhost:4200"})
@RestController
@RequestMapping("api")
public class MessageController {

	@Autowired
	private MessageService messageServ;
	
	@GetMapping("messages/{chatId}")
	public List<Message> getMessagesInChat(@PathVariable int chatId, Principal principal, HttpServletResponse res ){
		List<Message> messages = null;
		try {
			messages = messageServ.findByChatSortByDate(chatId, principal.getName());
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return messages;
	}
	
	@GetMapping("messages/{chatId}/after")
	public List<Message> getMessagesInChatAfterDate(@PathVariable int chatId, @RequestBody LocalDateTime date, Principal principal,
			HttpServletResponse res){
		List<Message> messages = null;
		try {
			messages = messageServ.getMessagesByDate(chatId, principal.getName(), date);
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return messages;
	}
	
	@PostMapping("messages/{chatId}")
	public Message createMessage(HttpServletResponse res, @RequestBody Message newMessage, Principal principal, @PathVariable int chatId ) {
		Message message = null;
		try {
			message = messageServ.createMessage(chatId, principal.getName(), newMessage);
			
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return message;
	}
	
	@PutMapping("messages/{id}")
	public Message editMessage(Principal principal, @RequestBody Message message, @PathVariable int id, HttpServletResponse res ) {
		try {
			message = messageServ.editMessage(id, message, principal.getName());
			
		}catch(Exception e){
			e.printStackTrace();
			res.setStatus(400);		}
		return message;
	}
	
	@DeleteMapping("messages/{id}")
	public void deleteMessage(Principal principal, @PathVariable int id, HttpServletResponse res, @RequestBody Message message) {
		try {
			boolean deleted = messageServ.deleteMessage(message, principal.getName());
			if(!deleted) {
				res.setStatus(401);
			}else {
				res.setStatus(204);
			}
		}catch(Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
	
}
