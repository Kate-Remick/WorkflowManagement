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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nmk.cardinal.entities.Workspace;
import com.nmk.cardinal.services.WorkspaceService;


@CrossOrigin({"*", "http://localhost:4200"})
@RestController
@RequestMapping("api")
public class WorkspaceController {
	
	@Autowired
	private WorkspaceService workspaceService;
	
	@GetMapping("workspaces/managed")
	public List<Workspace> indexManaged(HttpServletResponse res, Principal principal){
		List<Workspace> workspaces = workspaceService.getManagedWorkspaces(principal.getName());
		if(workspaces == null || workspaces.isEmpty()) {
			res.setStatus(404);
		}
		return workspaces;
	}
	
	@GetMapping("workspaces")
	public List<Workspace> index(HttpServletResponse res, Principal principal){
		List<Workspace> workspaces = workspaceService.getWorkspacesByUser(principal.getName());
		if(workspaces == null || workspaces.isEmpty()) {
			res.setStatus(404);
		}		
		return workspaces;
	}
	
	@PostMapping("workspaces")
	public Workspace create(HttpServletResponse res, HttpServletRequest req, Principal principal, @RequestBody Workspace workspace) {
		workspace = workspaceService.createNewWorkspace(workspace, principal.getName());
				try {
					if (workspace == null) {
						res.setStatus(404);
					} else {
						res.setStatus(201);
						StringBuffer url = req.getRequestURL();
						url.append("/").append(workspace.getId());
						res.setHeader("Location", url.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("Invalid Workspace Provided");
					res.setStatus(400);
					workspace = null;

				}
		return workspace;
	}
	
	@PutMapping("workspaces/{id}")
	public Workspace update(HttpServletResponse res, Principal principal, @PathVariable int id, @RequestBody Workspace workspace) {
		Workspace originalWS = workspaceService.show(id);
		
		try {
			if(originalWS != null) {
				workspace = workspaceService.editWorkspace(originalWS, workspace, principal.getName());
				if(workspace == null) {
					res.setStatus(400);
				} 
			}
			else {
				workspace = null;
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(500);
		}
		return workspace;
	}
	
	@DeleteMapping("workspaces/{id}")
	public void delete(HttpServletResponse res, @PathVariable int id, Principal principal) {
		Workspace workspace = workspaceService.show(id);
		try {	
			if(workspace != null) {
				workspaceService.activateDeactivateWorkspace(workspace, principal.getName());
				res.setStatus(200);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(500);
		}
	}
}
