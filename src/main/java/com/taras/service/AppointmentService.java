package com.taras.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taras.dto.appointment.AppointmentDto;
import com.taras.dto.appointment.AppointmentItemDto;
import com.taras.dto.appointment.HourAvailabilityDto;
import com.taras.model.Appointment;
import com.taras.model.Client;
import com.taras.model.Employee;
import com.taras.repository.AppointmentRepo;

@Service
public class AppointmentService {
	
	@Autowired
	private AppointmentRepo appointmentRepo;
	
	@Autowired
	private ScheduleService scheduleService; 

	public void createAppointment(AppointmentDto appointmentDto, com.taras.model.Service service, Client client,
			Employee employee) {
		Appointment appointment = new Appointment(appointmentDto.getAppointmentDate(), appointmentDto.getStartTime(), appointmentDto.getCancelled(),
				client, employee, service);
		
		appointmentRepo.save(appointment);
	}

	public void updateAppointment(AppointmentDto appointmentDto, Integer appointmentId, com.taras.model.Service service,
			Client client, Employee employee) throws Exception {
		Optional<Appointment> optionalAppointment = appointmentRepo.findById(appointmentId);
		if(!optionalAppointment.isPresent())
			throw new Exception("No Appointment");
		
		Appointment appointment = optionalAppointment.get();
		appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
		appointment.setStartTime(appointmentDto.getStartTime());
		appointment.setCancelled(appointmentDto.getCancelled());
		appointment.setModifiedDate(new Date());
		appointment.setClientId(client);
		appointment.setEmployee(employee);
		appointment.setService(service);
		
		appointmentRepo.save(appointment);
		
	}

	public void deleteAppointment(int appointmentId) throws Exception {
		if(!appointmentRepo.existsById(appointmentId))
			throw new Exception("No Appointment");
		appointmentRepo.deleteById(appointmentId);		
	}
	
	//to get the list of all the appointments and the complete schedule for the day 
	public List<AppointmentItemDto> getAppointmentsByDateAndEmployee (LocalDate date, Employee employee) {
		Appointment appointment; 
		List<LocalTime> scheduledHours = scheduleService.getListOfHoursOfScheduled(date, employee);
		List<AppointmentItemDto> appointmentList = new ArrayList<>(); 
		for (LocalTime scheduledHour : scheduledHours) {
			AppointmentItemDto appointmentItem = new AppointmentItemDto();
			appointmentItem.setAppointmentHour(scheduledHour);
			if (appointmentRepo.getAppointmentByDateAndEmployee(date, scheduledHour, employee.getId()).isEmpty()) {
				appointment = null;
			} else {
				appointment = appointmentRepo.getAppointmentByDateAndEmployee(date, scheduledHour, employee.getId()).get(0); 
			}
			appointmentItem.setAppointment(appointment);
			appointmentList.add(appointmentItem); 
		}
		return appointmentList; 
	}

	//to get the hours that are either available or booked 
	public List<HourAvailabilityDto> getHourAvailability(LocalDate appointmentDate, Employee employee) {
		
		List<AppointmentItemDto> appointmentsList = getAppointmentsByDateAndEmployee(appointmentDate, employee);
		List<HourAvailabilityDto> hoursList = new ArrayList<>(); 
		for (AppointmentItemDto appointmentItem : appointmentsList) {
			HourAvailabilityDto hourItem = new HourAvailabilityDto();
			hourItem.setHour(appointmentItem.getAppointmentHour());
			if (appointmentItem.getAppointment() == null) {
				hourItem.setAvailable(false);
			} else {
				hourItem.setAvailable(true);
			}
			hoursList.add(hourItem); 
		}
		return hoursList;
	}
	
	
	

}
