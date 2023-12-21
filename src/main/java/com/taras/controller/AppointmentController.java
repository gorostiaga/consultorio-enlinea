package com.taras.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taras.common.ApiResponse;
import com.taras.dto.appointment.AppointmentDto;
import com.taras.dto.appointment.AppointmentItemDto;
import com.taras.dto.appointment.HourAvailabilityDto;
import com.taras.model.Client;
import com.taras.model.Employee;
import com.taras.model.Service;
import com.taras.repository.ClientRepo;
import com.taras.repository.EmployeeRepo;
import com.taras.repository.ServiceRepo;
import com.taras.service.AppointmentService;

@RequestMapping("/appointment")
@RestController
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private ServiceRepo serviceRepo;
	
	@Autowired
	private ClientRepo clientRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo; 
	
	//create
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createAppointment (@RequestBody AppointmentDto appointmentDto) throws Exception {
		
		Optional<Service> optionalService = serviceRepo.findById(appointmentDto.getServiceId());		
		if (!optionalService.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "service does not exists"), HttpStatus.BAD_REQUEST);
        }
		
		Optional<Client> optionalClient = clientRepo.findById(appointmentDto.getClientId());		
		if (!optionalClient.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "client does not exists"), HttpStatus.BAD_REQUEST);
        }
		
		Optional<Employee> optionalEmployee = employeeRepo.findById(appointmentDto.getEmployeeId());		
		if (!optionalEmployee.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "employee does not exists"), HttpStatus.BAD_REQUEST);
        }
		
		appointmentService.createAppointment(appointmentDto, optionalService.get(), optionalClient.get(), optionalEmployee.get());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Appointment has been added"), HttpStatus.CREATED);        
	}
	
	
	//update
	@PutMapping("/update/{appointmentId}")
    public ResponseEntity<ApiResponse> updateEmployee(@PathVariable("appointmentId") Integer appointmentId, @RequestBody AppointmentDto appointmentDto) throws Exception {
		
		Optional<Service> optionalService = serviceRepo.findById(appointmentDto.getServiceId());		
		if (!optionalService.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "service does not exists"), HttpStatus.BAD_REQUEST);
        }
		
		Optional<Client> optionalClient = clientRepo.findById(appointmentDto.getClientId());		
		if (!optionalClient.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "client does not exists"), HttpStatus.BAD_REQUEST);
        }
		
		Optional<Employee> optionalEmployee = employeeRepo.findById(appointmentDto.getEmployeeId());		
		if (!optionalEmployee.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "employee does not exists"), HttpStatus.BAD_REQUEST);
        }
		
		appointmentService.updateAppointment(appointmentDto, appointmentId, optionalService.get(),optionalClient.get(), optionalEmployee.get());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Appointment has been updated"), HttpStatus.OK);
	}
	
	
	//delete
	@DeleteMapping("/delete/{appointmentId}")
	public ResponseEntity<ApiResponse> deleteEmployee (@PathVariable("appointmentId") int appointmentId) throws Exception {
		appointmentService.deleteAppointment(appointmentId); 
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Employee has been removed"), HttpStatus.OK);
	}
	
	
	//getAppointments by date and by employee
	@GetMapping(value="/{employeeId}/{appointmentDate}")
	public ResponseEntity<List<AppointmentItemDto>> getAppointmentsByDateAndEmployee(@PathVariable("employeeId") Integer employeeId, 
			@PathVariable("appointmentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate appointmentDate) {
		Optional<Employee> employee = employeeRepo.findById(employeeId);
		return ResponseEntity.ok(appointmentService.getAppointmentsByDateAndEmployee(appointmentDate, employee.get())); 
	}
	
	//get scheduled hours availability based on date and employee
	@GetMapping(value="/availability/{employeeId}/{appointmentDate}")
	public ResponseEntity<List<HourAvailabilityDto>> getHourAvailability (@PathVariable("employeeId") Integer employeeId, 
			@PathVariable("appointmentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate appointmentDate) {
		Employee employee = employeeRepo.getReferenceById(employeeId);
		return ResponseEntity.ok(appointmentService.getHourAvailability(appointmentDate, employee)); 
	}	
	
}
