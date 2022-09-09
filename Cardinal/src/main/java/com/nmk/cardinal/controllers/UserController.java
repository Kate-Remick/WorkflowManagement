package com.nmk.cardinal.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nmk.cardinal.entities.User;
import com.nmk.cardinal.services.UserService;

@CrossOrigin({"*", "http://localhost:4200"})
@RestController
@RequestMapping("api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("users")
	public List<User> index(HttpServletResponse res, Principal principal){
		List<User> users = userService.index();
		if(users == null) {
			res.setStatus(404);
		}
		
		return users;
	}
	@GetMapping("users/loggedIn")
	public User getLoggedInUser(HttpServletResponse res, Principal principal) {
		User user = null;
		try {
			user = userService.findByUsername(principal.getName());
			res.setStatus(200);
		}catch(Exception e) {
			res.setStatus(400);
		}
		return user;
	}
	
	@GetMapping("users/active")
	public List<User> indexActive(HttpServletResponse res, Principal principal){
		List<User> users = userService.indexActive();
		if(users == null) {
			res.setStatus(404);
		}
		
		return users;
	}
	
	@GetMapping("users/{id}")
	public User show(HttpServletResponse res, Principal principal, @PathVariable int id) {
		User user = userService.getUserById(id);
		if(user == null) {
			res.setStatus(404);
		}
		return user;
	}
	
	@PutMapping("users/{id}")
	public User update(HttpServletResponse res, Principal principal, @PathVariable int id, @RequestBody User user) {
		try {
			user = userService.update(principal.getName(), user, id);
			if (user == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return user;
	}
	
	@DeleteMapping("users/{id}")
	public void delete(HttpServletResponse res, @PathVariable int id, Principal principal) {
		try {
			if (userService.destroy(principal.getName(), id)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
	@PutMapping("users/reactivate/{id}")
	public void reactivate(HttpServletResponse res, HttpServletRequest req, @PathVariable int id, Principal principal) {
		try {
			if (userService.reactivate(principal.getName(), id)) {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(id);
				res.setHeader("Location", url.toString());
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
}
