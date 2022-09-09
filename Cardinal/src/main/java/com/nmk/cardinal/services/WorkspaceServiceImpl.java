package com.nmk.cardinal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmk.cardinal.entities.User;
import com.nmk.cardinal.entities.Workspace;
import com.nmk.cardinal.repositories.UserRepository;
import com.nmk.cardinal.repositories.WorkspaceRepository;



@Service
public class WorkspaceServiceImpl implements WorkspaceService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private WorkspaceRepository workRepo;
	
	@Override
	public List<Workspace> getManagedWorkspaces(String username) {
		
		return workRepo.findByManager_UsernameEquals(username);
	}

	@Override
	public List<Workspace> getWorkspacesByUser(String username) {
		return workRepo.findByUser(username);
	}

	@Override
	public Workspace createNewWorkspace(Workspace newWorkspace, String username) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			newWorkspace.setManager(user);
			newWorkspace = workRepo.saveAndFlush(newWorkspace);
		} else {
			newWorkspace = null;
		}
		return newWorkspace;
	}

	@Override
	public Workspace addUsers(int workspaceId, String username, List<String> users) {
		User user = userRepo.findByUsername(username);
		Workspace workspace = null;
		List<User> addedUsers = new ArrayList<>();
		Optional<Workspace> op = workRepo.findById(workspaceId);
		if(op.isPresent()) {
			workspace = op.get();
			if(user.equals(workspace.getManager())){
				for (String u : users) {
					User addedUser = userRepo.findByUsername(u);
					addedUsers.add(addedUser);
				}
				List<User> workspaceUsers = workspace.getUsers();
				workspaceUsers.addAll(addedUsers);
				workspace.setUsers(workspaceUsers);
				workspace = workRepo.saveAndFlush(workspace);
			}
		}
		return workspace;
	}

	@Override
	public Workspace addUser(int workspaceId, String username, String newUsername) {
		User user = userRepo.findByUsername(username);
		Workspace workspace = null;
		Optional<Workspace> op = workRepo.findById(workspaceId);
		if(op.isPresent()) {
			workspace = op.get();
			if(user.equals(workspace.getManager())){
				User addedUser = userRepo.findByUsername(newUsername);
				List<User> workspaceUsers = workspace.getUsers();
				workspaceUsers.add(addedUser);
				workspace.setUsers(workspaceUsers);
				workspace = workRepo.saveAndFlush(workspace);
			}
		}
		return workspace;
	}

	@Override
	public Workspace removeUser(Workspace workspace, String managerUsername, String username) {
		User manager = userRepo.findByUsername(managerUsername);
		User user = userRepo.findByUsername(username);
		if(manager.equals(workspace.getManager())) {
			List<User> workspaceUsers = workspace.getUsers();
			if(workspaceUsers.contains(user)) {
				workspaceUsers.remove(user);
				workspace.setUsers(workspaceUsers);
				workspace = workRepo.saveAndFlush(workspace);
			}
		}
		return workspace;
	}

	@Override
	public Workspace editWorkspace(Workspace workspace, Workspace newWorkspace, String managerUsername) {
		User manager = userRepo.findByUsername(managerUsername);
		if(manager.equals(workspace.getManager()) || manager.getRole().equals("ROLE_ADMIN")) {
			workspace.setDescription(newWorkspace.getDescription());
			workspace.setGoalDate(newWorkspace.getGoalDate());
			workspace.setName(newWorkspace.getName());
			workspace = workRepo.saveAndFlush(workspace);
		}
		return workspace;
	}

	@Override
	public void activateDeactivateWorkspace(Workspace workspace, String managerUsername) {
		User manager = userRepo.findByUsername(managerUsername);
		if(manager.equals(workspace.getManager()) || manager.getRole().equals("ROLE_ADMIN")) {
			workspace.setActive(!workspace.isActive());
			workspace = workRepo.saveAndFlush(workspace);
		}
		
		
		
		
	}

	@Override
	public Workspace show(int id) {
		Workspace workspace = null;
		Optional<Workspace> opt = workRepo.findById(id);
		if(opt.isPresent()) {
			workspace = opt.get();
		}
		return workspace;
	}

	@Override
	public Workspace getWorkspaceById(int id) {
		Workspace workspace = null;
		Optional<Workspace> op = workRepo.findById(id);
		if(op.isPresent()) {
			workspace = op.get();
		}
		return workspace;
	}

	
	
}
