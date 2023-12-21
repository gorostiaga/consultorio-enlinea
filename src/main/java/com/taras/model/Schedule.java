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

@Entity
@Table(name = "schedule")
public class Schedule {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private LocalDate date; 
    
    private Boolean recess;
    
    @Column(name = "start_time")
    private LocalTime startTime;
    
    @Column(name = "end_time")
    private LocalTime endTime;
    
    @Column(name = "start_break")
    private LocalTime startbreak;
    
    @Column(name = "end_break")
    private LocalTime endBreak;
    
    @Column(name = "created_date")
    private Date createdDate;
    
    @Column(name = " modified_date")
    private Date modifiedDate;

    
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee; 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Boolean getRecess() {
		return recess;
	}

	public void setRecess(Boolean recess) {
		this.recess = recess;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public LocalTime getStartbreak() {
		return startbreak;
	}

	public void setStartbreak(LocalTime startbreak) {
		this.startbreak = startbreak;
	}

	public LocalTime getEndBreak() {
		return endBreak;
	}

	public void setEndBreak(LocalTime endBreak) {
		this.endBreak = endBreak;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	public Schedule() {
		super();
	}

	public Schedule(LocalDate date, Boolean recess, LocalTime startTime, LocalTime endTime, LocalTime startbreak,
			LocalTime endBreak, Employee employee) {
		super();
		this.date = date;
		this.recess = recess;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startbreak = startbreak;
		this.endBreak = endBreak;
		this.employee = employee;
		this.createdDate = new Date();
		this.modifiedDate = new Date();
	}

	
    
    

}
