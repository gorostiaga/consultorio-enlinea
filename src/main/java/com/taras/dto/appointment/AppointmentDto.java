package com.taras.dto.appointment;

import java.time.LocalDate;
import java.time.LocalTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
	

	private LocalDate appointmentDate;
	private LocalTime startTime;
	private Boolean cancelled;
	private int clientId;
	private int employeeId;
	private int serviceId;

}
