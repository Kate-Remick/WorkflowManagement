package com.nmk.cardinal.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="workspace_id")
	private Workspace workspace;
	
	@OneToMany(mappedBy="chat")
	private List<Message> message;
	
	
	public Chat() {}
	
	

	public Workspace getWorkspace() {
		return workspace;
	}



	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}



	public List<Message> getMessage() {
		return message;
	}



	public void setMessage(List<Message> message) {
		this.message = message;
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
		Chat other = (Chat) obj;
		return id == other.id;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
