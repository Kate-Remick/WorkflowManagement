package com.nmk.cardinal.services;

import com.nmk.cardinal.entities.User;

public interface AuthService {
	public User register(User user);
	public User getUserByUsername(String username);
}
