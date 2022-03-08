package com.au.projecttracking.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log_work")
public class LogWork {
	@Id
	@Column(name = "log_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int logId;
	@Column(name = "ticket_id")
	private int ticketId;
	private String description;
	@Column(name = "time_spent")
	private int timeSpent;
	
	@Column(name = "remaining_time")
	private int timeRemaining;
	@Column(name = "date")
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
	
	public LogWork(int logId, int ticketId, String description, int timeSpent, int timeRemaining, LocalDate date) {
		super();
		this.logId = logId;
		this.ticketId = ticketId;
		this.description = description;
		this.timeSpent = timeSpent;
		this.timeRemaining = timeRemaining;
		this.date = date;
	}
	public LogWork() {
		super();
	}
}
