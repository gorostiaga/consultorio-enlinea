package com.taras.controller;

import java.util.Optional;

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
import com.taras.dto.employee.EmployeeDto;
import com.taras.exceptions.ServiceNotExistsException;
import com.taras.model.Service;
import com.taras.repository.ServiceRepo;
import com.taras.service.EmployeeService;

@RequestMapping("/employee")
@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService; 
	
	@Autowired
	private ServiceRepo serviceRepo; 
	
	//create a employee
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createEmployee (@RequestBody EmployeeDto employeeDto) throws ServiceNotExistsException {
		
		Optional<Service> optionalService = serviceRepo.findById(employeeDto.getServiceId()); 
		
		if (!optionalService.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "service does not exists"), HttpStatus.BAD_REQUEST);
        }
		
		employeeService.createService(employeeDto, optionalService.get());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been added"), HttpStatus.CREATED);
		
	}
	
	
	//update an employee
	@PutMapping("/update/{employeeId}")
    public ResponseEntity<ApiResponse> updateEmployee(@PathVariable("employeeId") Integer employeeId, @RequestBody EmployeeDto employeeDto) throws Exception {
		
		Optional<Service> optionalService = serviceRepo.findById(employeeDto.getServiceId()); 
		
		if (!optionalService.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "service does not exists"), HttpStatus.BAD_REQUEST);
        }
		
		employeeService.updateEmployee(employeeDto, employeeId, optionalService.get());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been updated"), HttpStatus.OK);
	}
	
	
	//delete an employee
	@DeleteMapping("/delete/{employeeId}")
	public ResponseEntity<ApiResponse> deleteEmployee (@PathVariable("employeeId") int employeeId) throws Exception {
		employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Employee has been removed"), HttpStatus.OK);
	}
	
}
