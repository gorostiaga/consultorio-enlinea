package com.taras.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taras.dto.service.ServiceDto;
import com.taras.repository.ServiceRepo;

@Service
public class ServiceService {
	
	@Autowired
	private ServiceRepo serviceRepo; 

	public void createService(String serviceName, int duration, double price) {
		com.taras.model.Service service = new com.taras.model.Service(serviceName, duration, price); 
		
		serviceRepo.save(service); 
	}

	public void updateService(ServiceDto serviceDto, Integer serviceId) throws Exception {
		Optional<com.taras.model.Service> optionalService = serviceRepo.findById(serviceId); 
		if (!optionalService.isPresent()) {
            throw new Exception("service not present");
        }
		
		com.taras.model.Service service = optionalService.get();
		service.setServiceName(serviceDto.getServiceName());
		service.setDuration(serviceDto.getDuration());;
		service.setPrice(serviceDto.getPrice());
		service.setModifiedDate(new Date());
		serviceRepo.save(service);
	}

	public void deleteService(int serviceId) throws Exception{
		
		if(!serviceRepo.existsById(serviceId))
			throw new Exception("There is no service");
		serviceRepo.deleteById(serviceId);
		
	}



}
