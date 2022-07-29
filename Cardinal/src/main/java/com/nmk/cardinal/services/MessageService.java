package com.nmk.cardinal.services;

import java.time.LocalDateTime;
import java.util.List;

import com.nmk.cardinal.entities.Chat;
import com.nmk.cardinal.entities.Message;


public interface MessageService {

	public List<Message> findByChatSortByDate(int chatId, String username);
	
	public List<Message> getMessagesByDate(int chatId, String username, LocalDateTime date); 
	
	public Message createMessage(Chat chat, String username, Message message);
	
	public Message editMessage(Message oldMessage, Message newMessage, String username);
	
	public boolean deleteMessage(Message message, String username);
	
	
}
