package com.au.projecttracking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {
	@Id
	@Column(name = "ticket_id")
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private int ticketId;
	@Column(name = "user_id")
	private int userId;
	
	private String title;
	private String description;
	private String comments;
	@Column(name = "estimated_time")
	private int estimatedTime;
	private String status;
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getEstimatedTime() {
		return estimatedTime;
	}
	public void setEstimatedTime(int estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Ticket(int ticketId, int userId, String title, String description, String comments, int estimatedTime,
			String status) {
		super();
		this.ticketId = ticketId;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.comments = comments;
		this.estimatedTime = estimatedTime;
		this.status = status;
	}
	public Ticket() {
		super();
	}
	
}
