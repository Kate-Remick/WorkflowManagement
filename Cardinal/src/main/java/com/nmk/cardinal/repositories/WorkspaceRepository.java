package com.nmk.cardinal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nmk.cardinal.entities.Workspace;

public interface WorkspaceRepository extends JpaRepository<Workspace, Integer> {
	
	public List<Workspace> findByManager_UsernameEquals(@Param("username") String username);
	
	@Query("SELECT w FROM Workspace w JOIN w.users wu WHERE wu.username = username")
	public List<Workspace> findByUser(@Param("username") String username);

}
