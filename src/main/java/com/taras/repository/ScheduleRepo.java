package com.taras.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.taras.model.Employee;
import com.taras.model.Schedule;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Integer>{
	
	@Query("SELECT s FROM Schedule s WHERE s.date = :date AND s.employee = :employee")
	public List<Schedule> getSchedulesByDateAndEmployee (@Param("date") LocalDate date, @Param("employee") Employee employee); 

}
