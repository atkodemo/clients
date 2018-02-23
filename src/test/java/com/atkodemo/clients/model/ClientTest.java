package com.atkodemo.clients.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.atkodemo.clients.model.Client;

public class ClientTest {

	Client client;
	
	@Before
	public void setUp() throws Exception {
		client = new Client();
	}
	
	@Test
	public void testGetId() {
		Long id = 10L;
		client.setId(id);
		assertEquals(id, client.getId());
	}

	@Test
	public void testGetName() {
		String name = "Client 10";
		client.setName(name);
		assertEquals(name, client.getName());
	}

}
