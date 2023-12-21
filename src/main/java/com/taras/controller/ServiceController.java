package com.taras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taras.common.ApiResponse;
import com.taras.dto.service.ServiceDto;
import com.taras.service.ServiceService;

@RequestMapping("/service")
@RestController
public class ServiceController {
	
	@Autowired
	private ServiceService serviceService; 
	
	//create service
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createService (@RequestBody ServiceDto serviceDto) {
		
		serviceService.createService(serviceDto.getServiceName(), serviceDto.getDuration(), serviceDto.getPrice());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "service has been added"), HttpStatus.CREATED);
		
	}
	
	
	//update service
	@PutMapping("/update/{serviceId}")
    public ResponseEntity<ApiResponse> updateService(@PathVariable("serviceId") Integer serviceId, @RequestBody ServiceDto serviceDto) throws Exception {
		
		serviceService.updateService(serviceDto, serviceId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been updated"), HttpStatus.OK);
	}
	
	//delete service
	@DeleteMapping("/delete/{serviceId}")
	public ResponseEntity<ApiResponse> deleteEmployee (@PathVariable("serviceId") int serviceId) throws Exception {
		serviceService.deleteService(serviceId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Service has been removed"), HttpStatus.OK);
	}
}
