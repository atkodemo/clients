package com.atkodemo.clients.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.atkodemo.clients.controller.ClientController;
import com.atkodemo.clients.model.Client;
import com.atkodemo.clients.service.ClientService;

public class ClientControllerTest {

	ClientController clientController;

	@Mock
	ClientService clientService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		clientController = new ClientController(clientService);
	}
	
	@Test
	public void testShowClient() throws Exception {

		Client client = new Client();
		client.setId(10L);
		
		when(clientService.getById(anyLong())).thenReturn(client);

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
		
		mockMvc
			.perform(get("/show/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("client/clientshow"))
			.andExpect(model().attributeExists("client"));
	}

}
