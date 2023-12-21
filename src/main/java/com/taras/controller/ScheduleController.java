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
import com.taras.dto.schedule.ScheduleDto;
import com.taras.model.Employee;
import com.taras.repository.EmployeeRepo;
import com.taras.service.ScheduleService;

@RequestMapping("/schedule")
@RestController
public class ScheduleController {
	
	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	EmployeeRepo employeeRepo; 
	
	//create
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createSchedule (@RequestBody ScheduleDto scheduleDto) throws Exception {
		
		Optional<Employee> optionalEmployee = employeeRepo.findById(scheduleDto.getEmployeeId()); 
		
		if (!optionalEmployee.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "employee does not exists"), HttpStatus.BAD_REQUEST);
        }
		
		scheduleService.createSchedule(scheduleDto, optionalEmployee.get());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Schedule has been added"), HttpStatus.CREATED);
		
	}
	
	//update
	@PutMapping("/update/{scheduleId}")
    public ResponseEntity<ApiResponse> updateSchedule(@PathVariable("scheduleId") Integer scheduleId, @RequestBody ScheduleDto scheduleDto) throws Exception {
		
		Optional<Employee> optionalEmployee = employeeRepo.findById(scheduleDto.getEmployeeId()); 
		
		if (!optionalEmployee.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "employee does not exists"), HttpStatus.BAD_REQUEST);
        }
		
		scheduleService.updateSchedule(scheduleDto, scheduleId, optionalEmployee.get());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Schedule has been updated"), HttpStatus.OK);
	}

	
	//delete 
	@DeleteMapping("/delete/{scheduleId}")
	public ResponseEntity<ApiResponse> deleteSchedule (@PathVariable("scheduleId") int scheduleId) throws Exception {
		scheduleService.deleteSchedule(scheduleId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Schedule has been removed"), HttpStatus.OK);
	}
	
	//schedule all the remaining dates of the month after the entered date
	@PostMapping("/setMonth")
	public ResponseEntity<ApiResponse> setMonth (@RequestBody ScheduleDto scheduleDto) throws Exception {
		
		Optional<Employee> optionalEmployee = employeeRepo.findById(scheduleDto.getEmployeeId()); 
		
		if (!optionalEmployee.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "employee does not exists"), HttpStatus.BAD_REQUEST);
        }
				
		scheduleService.setMonth(scheduleDto, optionalEmployee.get());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Schedule has been added for the rest of the month"), HttpStatus.CREATED);
		
	}
}
