package com.atkodemo.clients.repository;

import org.springframework.data.repository.CrudRepository;

import com.atkodemo.clients.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {

}
