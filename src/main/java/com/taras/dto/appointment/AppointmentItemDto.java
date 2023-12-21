package com.taras.dto.appointment;

import java.time.LocalTime;

import com.taras.model.Appointment;

import lombok.Data;

@Data
public class AppointmentItemDto {
	
	private LocalTime appointmentHour;
	private Appointment appointment; 

}
