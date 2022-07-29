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

class DeckTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Deck deck;

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
	    deck = em.find(Deck.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	    em.close();
	    deck = null;
	}

	@Test
	@DisplayName("testing basic user mappings")
	void test1() {
		assertNotNull(deck);
		assertEquals(deck.getName(), "test deck 1");
	}
	@Test
	@DisplayName("testing deck to workspace mappings")
	void test2() {
		assertNotNull(deck.getWorkspace().getName(), "testWorkspace");
	}
	@Test
	@DisplayName("testing deck to card mappings")
	void test3() {
		assertNotNull(deck.getCards().size() > 0);
	}

}
