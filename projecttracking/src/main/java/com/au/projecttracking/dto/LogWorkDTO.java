package com.au.projecttracking.dto;

import java.time.LocalDate;

public class LogWorkDTO {
	
	private int logId;
	
	private int ticketId;
	private String description;
	
	private int timeSpent;
	
	private int timeRemaining;
	
	private LocalDate date;
	
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	
	public int getTimeSpent() {
		return timeSpent;
	}
	public void setTimeSpent(int timeSpent) {
		this.timeSpent = timeSpent;
	}
	public int getTimeRemaining() {
		return timeRemaining;
	}
	public void setTimeRemaining(int timeRemaining) {
		this.timeRemaining = timeRemaining;
	}
	
		
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	public LogWorkDTO(int logId, int ticketId, String description, int timeSpent, int timeRemaining,
			LocalDate date) {
		super();
		this.logId = logId;
		this.ticketId = ticketId;
		this.description = description;
		this.timeSpent = timeSpent;
		this.timeRemaining = timeRemaining;
		this.date = date;
	}
	public LogWorkDTO() {
		super();
	}
	
	
	

}
