package com.nmk.cardinal.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmk.cardinal.entities.Chat;
import com.nmk.cardinal.entities.Message;
import com.nmk.cardinal.entities.User;
import com.nmk.cardinal.repositories.ChatRepository;
import com.nmk.cardinal.repositories.MessageRepository;
import com.nmk.cardinal.repositories.UserRepository;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MessageRepository messageRepo;
	
	@Autowired
	private ChatRepository chatRepo;
	
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
	public Message createMessage(int id, String username, Message message) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			Optional<Chat> op = chatRepo.findById(id);
			if(op.isPresent()) {
				message.setChat(op.get());
				message.setUser(user);
				message = messageRepo.saveAndFlush(message);
			}
		}
		return message;
	}

	@Override
	public Message editMessage(int id, Message newMessage, String username) {
		User user = userRepo.findByUsername(username);
		Message oldMessage = null;
		Optional<Message> op = messageRepo.findById(id);
		if(op.isPresent()) {
			oldMessage = op.get();
		}
		
		if(oldMessage != null && user.equals(oldMessage.getUser())) {
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
