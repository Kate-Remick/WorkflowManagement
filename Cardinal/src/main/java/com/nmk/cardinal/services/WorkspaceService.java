package com.nmk.cardinal.services;

import java.util.List;

import com.nmk.cardinal.entities.Workspace;

public interface WorkspaceService {

	public List<Workspace> getManagedWorkspaces(String username);
	
	public List<Workspace> getWorkspacesByUser(String username);
	
	public Workspace createNewWorkspace(Workspace newWorkspace, String username);
	
	public Workspace addUsers(int workspaceId, String username, List<String> users);
	
	public Workspace addUser(int workspaceId, String username, String newUsername);
	
	public Workspace removeUser(Workspace workspace, String managerUsername, String username);
		
	public void activateDeactivateWorkspace(Workspace workspace, String managerUsername);

	public Workspace editWorkspace(Workspace workspace, Workspace newWorkspace, String managerUsername);
	
	public Workspace show(int id);
	
	
	
}
