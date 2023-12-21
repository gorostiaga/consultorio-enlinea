package com.taras.dto.schedule;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {
	
	private LocalDate date;    
    private Boolean recess;
    @JsonFormat(pattern= "HH:mm", shape= JsonFormat.Shape.STRING)
    private LocalTime startTime;
    @JsonFormat(pattern= "HH:mm", shape= JsonFormat.Shape.STRING)
    private LocalTime endTime;
    @JsonFormat(pattern= "HH:mm", shape= JsonFormat.Shape.STRING)
    private LocalTime startbreak;
    @JsonFormat(pattern= "HH:mm", shape= JsonFormat.Shape.STRING)
    private LocalTime endBreak;
    private int employeeId;

}
