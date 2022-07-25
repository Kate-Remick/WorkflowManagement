package com.nmk.cardinal.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WorkspaceTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Workspace workspace;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("WorkflowManagementJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}
	@BeforeEach
	void setUp() throws Exception {
	    em = emf.createEntityManager();
	    workspace = em.find(Workspace.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	    em.close();
	    workspace = null;
	}

	@Test
	@DisplayName("testing basic user mappings")
	void test1() {
		assertNotNull(workspace);
		assertEquals(workspace.getName(), "testWorkspace");
		
	}

}
