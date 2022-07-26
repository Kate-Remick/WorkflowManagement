package com.nmk.cardinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nmk.cardinal.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsername(String username);
}
