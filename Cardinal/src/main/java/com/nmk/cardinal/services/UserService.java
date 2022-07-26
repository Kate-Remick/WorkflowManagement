package com.nmk.cardinal.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nmk.cardinal.entities.User;

public interface UserService extends UserDetailsService {
	
	public User getUserById(int id);
	
	public List<User> index();
	
	public List<User> indexActive();
	
	public User show(String username, int id);
	
	public User create(User user);
	
	public User update(String username, User user, int id);
	
	public boolean destroy(String username, int id);
	
	public boolean reactivate(String username, int id);

}
