package com.taras.dto.appointment;

import java.time.LocalTime;

import lombok.Data;

@Data
public class HourAvailabilityDto {
	
	private LocalTime hour;
	private Boolean available; 
}
