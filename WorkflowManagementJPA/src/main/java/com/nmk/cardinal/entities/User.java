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
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String email;
	
	private String username;
	
	private String password;
	
	@Column(name="img_url")
	private String imgUrl;
	
	@Column(name="google_id")
	private String googleId;
	
	@CreationTimestamp
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	private boolean active;
	
	private String role;
	
	@ManyToMany
	@JoinTable(name="user_workspace", joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns = @JoinColumn(name="workspace_id"))
	@JsonIgnore
	private List<Workspace> workspaces;
	
	@OneToMany(mappedBy="manager")
	@JsonIgnore
	private List<Workspace> managedWorkspaces;
	
	@OneToMany(mappedBy="user")
	@JsonIgnore
	private List<Message> messages;
	
	@OneToMany(mappedBy="assignedUser")
	@JsonIgnore
	private List<Card> cards;
	
	public User() {}
	

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", imgUrl=" + imgUrl + ", googleId=" + googleId
				+ ", active=" + active + ", role=" + role + ", workspaces=" + workspaces + ", managedWorkspaces="
				+ managedWorkspaces + ", messages=" + messages + ", cards=" + cards + "]";
	}





	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Workspace> getWorkspaces() {
		return workspaces;
	}



	public void setWorkspaces(List<Workspace> workspaces) {
		this.workspaces = workspaces;
	}



	public List<Workspace> getManagedWorkspaces() {
		return managedWorkspaces;
	}



	public void setManagedWorkspaces(List<Workspace> managedWorkspaces) {
		this.managedWorkspaces = managedWorkspaces;
	}



	public List<Message> getMessages() {
		return messages;
	}



	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}



	public List<Card> getCards() {
		return cards;
	}



	public void setCards(List<Card> cards) {
		this.cards = cards;
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
		User other = (User) obj;
		return id == other.id;
	}
	
	
	
	
}
