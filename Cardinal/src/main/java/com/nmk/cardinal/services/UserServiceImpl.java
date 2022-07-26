package com.nmk.cardinal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nmk.cardinal.entities.User;
import com.nmk.cardinal.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User getUserById(int id) {
		Optional<User> userOpt = userRepo.findById(id);
		User user = null;
		if (userOpt.isPresent()) {
			user = userOpt.get();
		}
		return user;
	}

	@Override
	public List<User> index() {
		
		List<User> users = null;
		
		users = userRepo.findAll();

		return users;
	}
	
	public List<User> indexActive(){
		
		List<User> users = null;
		
		users = userRepo.findAll();
		
		List<User> activeUsers = new ArrayList<>();
		
		if(users != null & !users.isEmpty()) {
			for(User user: users) {
				if (user.isActive()) {
					activeUsers.add(user);
				}
			}
		}
		
		if (activeUsers.isEmpty()) {
			activeUsers = null;
		}
		
		return activeUsers;
		
	}

	@Override
	public User show(String username, int id) {
		User user = null;
		Optional<User> userOpt = userRepo.findById(id);
		if(userOpt.isPresent()) {
			user = userOpt.get();
		}
		return user;
	}

	@Override
	public User create(User user) {
		User created = null;
		if(user != null) {
			created = user;
			userRepo.saveAndFlush(created);
		}
		return created;
	}

	@Override
	public User update(String username, User user, int id) {
		User existing = null;
		User sessionUser = userRepo.findByUsername(username);
		Optional<User> op = userRepo.findById(id);
		if(op.isPresent()) {
			existing = op.get();
		}
		if (existing != null && (existing.getUsername().equals(username) || sessionUser.getRole().equals("role_admin"))) {
			existing.setFirstName(user.getFirstName());
			existing.setLastName(user.getLastName());
			existing.setImgUrl(user.getImgUrl());
			existing.setEmail(user.getEmail());
			existing = userRepo.saveAndFlush(existing);
		}
		
		return existing;
	}

	@Override
	public boolean destroy(String username, int id) {
		
		User existing = null;
		User sessionUser = userRepo.findByUsername(username);
		Optional<User> op = userRepo.findById(id);
		
		if(op.isPresent()) {
			existing = op.get();
		}
		
		if (existing != null && (existing.getUsername().equals(username) || sessionUser.getRole().equals("role_admin"))) {
			existing.setActive(false);
		}
		
		return !existing.isActive();
	}
	
	public boolean reactivate(String username, int id) {
		User existing = null;
		User sessionUser = userRepo.findByUsername(username);
		Optional<User> op = userRepo.findById(id);
		
		if(op.isPresent()) {
			existing = op.get();
		}
		
		if (existing != null && (existing.getUsername().equals(username) || sessionUser.getRole().equals("role_admin"))) {
			existing.setActive(true);
		}
		
		return existing.isActive();		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
