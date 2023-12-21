package com.taras.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taras.model.Appointment;
import com.taras.model.Employee;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer>{
	
	//@Query("SELECT a FROM Appointment a INNER JOIN a.employee e WHERE a.appointmentDate = :appointment_date AND e = :employee AND s.startTime = :start_time")
	
	//public List<Appointment> getAppointmentByDateAndEmployee (@Param("appointment_date") LocalDate appointmentDate, @Param("employee_id") Employee employee, @Param("start_time") LocalTime startTime);
	@Query(value = "SELECT * FROM appointment WHERE appointment_date = ?1 AND start_time = ?2 AND employee_id = ?3", nativeQuery = true)
	List<Appointment> getAppointmentByDateAndEmployee(LocalDate appointmentDate, LocalTime startTime, int employeeId);

}
