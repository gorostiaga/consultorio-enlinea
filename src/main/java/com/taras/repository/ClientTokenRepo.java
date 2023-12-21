package com.taras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taras.model.Client;
import com.taras.model.ClientToken;



public interface ClientTokenRepo extends JpaRepository<ClientToken, Integer>{
	
	ClientToken findByClient(Client Client);
	ClientToken findByToken(String token);

}
