package com.nmk.cardinal.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@CreationTimestamp
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(name="chat_id")
	private Chat chat;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	public Message() {}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return id == other.id;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public Chat getChat() {
		return chat;
	}



	public void setChat(Chat chat) {
		this.chat = chat;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
	
}
