package com.nmk.cardinal.services;

import java.util.List;

import com.nmk.cardinal.entities.User;

public interface UserService {
	
	public User getUserById(int id);
	
	public List<User> index();
	
	public List<User> indexActive();
	
	public User show(String username, int id);
	
	public User create(User user);
	
	public User update(String username, User user, int id);
	
	public boolean destroy(String username, int id);
	
	public boolean reactivate(String username, int id);
	
	public User findByUsername(String username);

}
