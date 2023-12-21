package com.taras.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.taras.dto.employee.EmployeeDto;
import com.taras.model.Employee;
import com.taras.model.Service;
import com.taras.repository.EmployeeRepo;

@org.springframework.stereotype.Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepo employeeRepo;	


	public void createService(EmployeeDto employeeDto, Service service) {
		Employee employee = new Employee(employeeDto.getFistName(), employeeDto.getLastName(), service); 
		employeeRepo.save(employee); 
		
	}

	public void updateEmployee(EmployeeDto employeeDto, Integer employeeId, Service service) throws Exception{
		Optional<Employee> optionalEmployee = employeeRepo.findById(employeeId); 
		if (!optionalEmployee.isPresent()) {
            throw new Exception("employee not present");
        }
		
		Employee employee = optionalEmployee.get(); 
		employee.setFirstName(employeeDto.getFistName());
		employee.setLastName(employeeDto.getLastName());
		employee.setModifiedDate(new Date());
		employee.setService(service);
		
		employeeRepo.save(employee);
		
	}

	public void deleteEmployee(int employeeId) throws Exception {
		
		if(!employeeRepo.existsById(employeeId)) 
			throw new Exception("No employee exists");
		employeeRepo.deleteById(employeeId);	
		
	}



}
