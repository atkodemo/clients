package com.atkodemo.clients.bootstrap;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.atkodemo.clients.model.Client;
import com.atkodemo.clients.repository.ClientRepository;

@Component
public class ClientBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final ClientRepository clientRepository;
	
	@Autowired
	public ClientBootstrap(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		clientRepository.saveAll(initClients());
		
		logger.info("Initialized CLIENT table with two records");
	}

	private List<Client> initClients() {
		List<Client> clients = new ArrayList<>(2);

		Client client1 = new Client();
		client1.setName("Client_1");
		client1.setCountry("Switzerland");
		client1.setCity("Geneva");
		client1.setAddress("Rue de Grand-Pre 80.");
		client1.setZip("1202");
		client1.setDeleted(Boolean.FALSE);
		
		Client client2 = new Client();
		client2.setName("Client_2");
		client2.setCountry("Hungary");
		client2.setCity("Budapest");
		client2.setAddress("Jakab u. 16.");
		client2.setZip("1108");
		client2.setDeleted(Boolean.FALSE);
		
		clients.add(client1);
		clients.add(client2);
		
		return clients;
	}

}
