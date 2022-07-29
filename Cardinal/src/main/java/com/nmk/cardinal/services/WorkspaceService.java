package com.nmk.cardinal.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nmk.cardinal.entities.Workspace;

@Service
public interface WorkspaceService {

	public List<Workspace> getManagedWorkspaces(String username);
	
	public List<Workspace> getWorkspacesByUser(String username);
	
	public Workspace createNewWorkspace(Workspace newWorkspace, String username);
	
	public Workspace addUsers(int workspaceId, String username, List<String> users);
	
	public Workspace addUser(int workspaceId, String username);
	
	public Workspace removeUser(Workspace workspace, String managerUsername, String username);
	
	public Workspace editWorkspace(Workspace workspace, Workspace newWorkspace, String managerUsername);
	
	public void activateDeactivateWorkspace(Workspace workspace, String managerUsername);
	
	
	
	
}
