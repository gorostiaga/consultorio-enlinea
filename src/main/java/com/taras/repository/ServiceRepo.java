package com.taras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taras.model.Service;

@Repository
public interface ServiceRepo extends JpaRepository<Service, Integer>{

}
