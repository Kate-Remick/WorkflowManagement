package com.nmk.cardinal.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmk.cardinal.entities.Chat;
import com.nmk.cardinal.entities.Message;
import com.nmk.cardinal.entities.User;
import com.nmk.cardinal.repositories.MessageRepository;
import com.nmk.cardinal.repositories.UserRepository;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MessageRepository messageRepo;
	
	@Override
	public List<Message> findByChatSortByDate(int chatId, String username) {
		User user = userRepo.findByUsername(username);
		List<Message> messages = null;
		if(user != null) {
			messages = messageRepo.findByChatId_OrderByCreatedAt(chatId);
		}
		return messages;
	}

	
	//Not Yet properly implemented
	@Override
	public List<Message> getMessagesByDate(int chatId, String username, LocalDateTime date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message createMessage(Chat chat, String username, Message message) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			message.setChat(chat);
			message.setUser(user);
			message = messageRepo.saveAndFlush(message);
		}
		return message;
	}

	@Override
	public Message editMessage(Message oldMessage, Message newMessage, String username) {
		User user = userRepo.findByUsername(username);
		if(user.equals(oldMessage.getUser())) {
			oldMessage.setContent(newMessage.getContent());
			oldMessage = messageRepo.saveAndFlush(oldMessage);
		}
		return oldMessage;
	}

	@Override
	public boolean deleteMessage(Message message, String username) {
		User user = userRepo.findByUsername(username);
		if(user.equals(message.getUser())) {
			messageRepo.delete(message);
		}
		return !messageRepo.existsById(message.getId());
	}

	
	
}
