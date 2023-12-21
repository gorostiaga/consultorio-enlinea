package com.taras.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taras.dto.schedule.ScheduleDto;
import com.taras.model.Employee;
import com.taras.model.Schedule;
import com.taras.repository.ScheduleRepo;

@Service
public class ScheduleService {
	
	@Autowired
	ScheduleRepo scheduleRepo;

	public void createSchedule(ScheduleDto scheduleDto, Employee employee) {
		
		Schedule schedule = new Schedule(scheduleDto.getDate(), scheduleDto.getRecess(), scheduleDto.getStartTime(), scheduleDto.getEndTime(), 
				scheduleDto.getStartbreak(), scheduleDto.getEndBreak(), employee);
		scheduleRepo.save(schedule);		
	}

	public void updateSchedule(ScheduleDto scheduleDto, Integer scheduleId, Employee employee) throws Exception {
		Optional<Schedule> optionalSchedule = scheduleRepo.findById(scheduleId); 
		if(!optionalSchedule.isPresent())
			throw new Exception("No Schedule");
		
		Schedule schedule = optionalSchedule.get();
		schedule.setDate(scheduleDto.getDate());
		schedule.setRecess(scheduleDto.getRecess());
		schedule.setStartTime(scheduleDto.getStartTime()); 
		schedule.setEndTime(scheduleDto.getEndTime());
		schedule.setStartbreak(scheduleDto.getStartbreak());
		schedule.setEndBreak(scheduleDto.getEndBreak());
		schedule.setModifiedDate(new Date());
		schedule.setEmployee(employee);
		
		scheduleRepo.save(schedule);			
	}

	public void deleteSchedule(int scheduleId) throws Exception{
		if(!scheduleRepo.existsById(scheduleId))
			throw new Exception("No Schedule");
		scheduleRepo.deleteById(scheduleId);
		
	}
	
	@Transactional
	public void setMonth(ScheduleDto scheduleDto, Employee employee) {
		LocalDate firstDayOfNextMonth = scheduleDto.getDate().with(TemporalAdjusters.firstDayOfNextMonth());
		for(LocalDate date = scheduleDto.getDate(); date.isBefore(firstDayOfNextMonth); date.plusDays(1)) {
			Schedule schedule = new Schedule(date, scheduleDto.getRecess(), scheduleDto.getStartTime(), scheduleDto.getEndTime(), 
					scheduleDto.getStartbreak(), scheduleDto.getEndBreak(), employee);
			scheduleRepo.save(schedule);
		}		
	}
	
	public List<LocalTime> getListOfHoursOfScheduled (LocalDate date, Employee employee) {
		List<Schedule> listSchedule = scheduleRepo.getSchedulesByDateAndEmployee(date, employee); 
		Schedule schedule = listSchedule.get(0); 
		List<LocalTime> hoursOfScheduled = new ArrayList<>();
		LocalTime startHour = schedule.getStartTime();
		if (schedule.getRecess()) {
			while(startHour.compareTo(schedule.getStartbreak())<0) {
				hoursOfScheduled.add(startHour); 
				startHour = startHour.plusMinutes(schedule.getEmployee().getService().getDuration()); 
			}
			startHour = schedule.getEndBreak();
			while (startHour.compareTo(schedule.getEndTime())<0) {
				hoursOfScheduled.add(startHour); 
				startHour = startHour.plusMinutes(schedule.getEmployee().getService().getDuration()); 
			}
			
		} else {
			while (startHour.compareTo(schedule.getEndTime())<0) {
				hoursOfScheduled.add(startHour); 
				startHour = startHour.plusMinutes(schedule.getEmployee().getService().getDuration());
			}			
		}
		
		return hoursOfScheduled; 		
	}

}
