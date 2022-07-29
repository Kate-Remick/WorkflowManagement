package com.nmk.cardinal.entities;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChatTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Chat chat;

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
	    chat = em.find(Chat.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	    em.close();
	    chat = null;
	}

	@Test
	@DisplayName("testing basic user mappings")
	void test1() {
		assertNotNull(chat);
	}
	@Test
	@DisplayName("testing chat to message mapping")
	void test2() {
		assertNotNull(chat.getMessages().size() > 0);
	}
	@Test
	@DisplayName("testing chat to workspace mapping")
	void test3() {
		assertNotNull(chat.getWorkspace().getName(), "testWorkspace");
	}
	

}
