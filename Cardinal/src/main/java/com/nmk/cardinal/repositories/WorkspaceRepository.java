package com.nmk.cardinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nmk.cardinal.entities.Workspace;

public interface WorkspaceRepository extends JpaRepository<Workspace, Integer> {

}
