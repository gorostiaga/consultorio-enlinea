package com.taras.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "appointment")
@Data
public class Appointment {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "created_date")
    private Date createdDate;
    
    @Column(name = "modified_date")
    private Date modifiedDate;
    
    @Column(name = "appointment_date")
    private LocalDate appointmentDate;
    
    @Column(name = "start_time")
    private LocalTime startTime;
    
    private Boolean cancelled;
    
    @ManyToOne
    @JoinColumn(nullable = false, name = "client_id")
    private Client clientId;
    
    @ManyToOne
    @JoinColumn(nullable = false, name = "employee_id")
    private Employee employee; 
    
    @ManyToOne
    @JoinColumn(nullable = false, name = "service_id")
    private Service service;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public Boolean getCancelled() {
		return cancelled;
	}

	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}

	public Client getClientId() {
		return clientId;
	}

	public void setClientId(Client clientId) {
		this.clientId = clientId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Appointment() {
		super();
	}

	public Appointment(LocalDate appointmentDate, LocalTime startTime, Boolean cancelled, Client clientId, Employee employee,
			Service service) {
		super();
		this.appointmentDate = appointmentDate;
		this.startTime = startTime;
		this.cancelled = cancelled;
		this.clientId = clientId;
		this.employee = employee;
		this.service = service;
		this.createdDate = new Date();
		this.modifiedDate = new Date();
	} 
    
    

}
