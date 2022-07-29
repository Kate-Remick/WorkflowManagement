package com.nmk.cardinal.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Workspace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	@CreationTimestamp
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="goal_date")
	private LocalDateTime goalDate;
	
	private boolean active;
	
	@ManyToMany
	@JoinTable(name="user_workspace", joinColumns=@JoinColumn(name="workspace_id"),
	inverseJoinColumns = @JoinColumn(name="user_id"))
	@JsonIgnore
	private List<User> users;
	
	@OneToMany(mappedBy="workspace")
	private List<Deck> decks;
	
	@OneToMany(mappedBy="workspace")
	private List<Chat> chats;
	
	@ManyToOne
	@JoinColumn(name="manager_id")
	private User manager; 
	
	public Workspace() {}
	
	

	@Override
	public String toString() {
		return "Workspace [id=" + id + ", name=" + name + ", description=" + description + ", createdAt=" + createdAt
				+ ", goalDate=" + goalDate + ", active=" + active + ", users=" + users + ", decks=" + decks + ", chats="
				+ chats + ", manager=" + manager + "]";
	}



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
		Workspace other = (Workspace) obj;
		return id == other.id;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getGoalDate() {
		return goalDate;
	}

	public void setGoalDate(LocalDateTime goalDate) {
		this.goalDate = goalDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}



	public List<User> getUsers() {
		return users;
	}



	public void setUsers(List<User> users) {
		this.users = users;
	}



	public List<Deck> getDecks() {
		return decks;
	}



	public void setDecks(List<Deck> decks) {
		this.decks = decks;
	}



	public List<Chat> getChats() {
		return chats;
	}



	public void setChats(List<Chat> chats) {
		this.chats = chats;
	}



	public User getManager() {
		return manager;
	}



	public void setManager(User manager) {
		this.manager = manager;
	}
	
	
	

}
