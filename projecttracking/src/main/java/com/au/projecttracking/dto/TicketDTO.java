package com.au.projecttracking.dto;

public class TicketDTO {
	
	private int ticketId;
	
	private int userId;
	
	private String title;
	private String description;
	private String comments;
	
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
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public TicketDTO(int ticketId, int userId, String title, String description, String comments, int estimatedTime,
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
	public TicketDTO() {
		super();
	}
	
}
