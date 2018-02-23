package com.atkodemo.clients.service;

import com.atkodemo.clients.model.Client;

public interface ClientService {

	Client getById(Long id);
	
	Client saveOrUpdate(Client client);
	
	Iterable<Client> list();
        
    void setDeletedFlag(Long id, Boolean isDeleted);
    
}
