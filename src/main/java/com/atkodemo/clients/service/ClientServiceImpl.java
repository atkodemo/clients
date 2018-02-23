package com.atkodemo.clients.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atkodemo.clients.model.Client;
import com.atkodemo.clients.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;

	@Autowired
	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	@Override
	public Client getById(Long id) {
		Optional<Client> clientOptional = clientRepository.findById(id);
		
		if (!clientOptional.isPresent()) {
			throw new RuntimeException("Client not found: " + id);
		}
		
		return clientOptional.get();
	}

	@Override
	public Client saveOrUpdate(Client client) {
        Client savedClient = clientRepository.save(client);
        return savedClient;
	}

	@Override
	public Iterable<Client> list() {
		return clientRepository.findAll(); 
	}

	@Override
	public void setDeletedFlag(Long id, Boolean isDeleted) {
		Client client = getById(id);
		client.setDeleted(isDeleted);
		saveOrUpdate(client);
	}

}
