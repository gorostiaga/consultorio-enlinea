package com.taras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taras.model.Client;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer>{
	
	Client findByPhone(int phone);

}
