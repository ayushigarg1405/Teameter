package com.au.projecttracking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class Login {
	
	@Id
	@Column(name = "login_id")
	private int loginId;
	private String username;
	private String password;
	private String designation;
	
	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	
	public Login(int loginId, String username, String password, String designation) {
		super();
		this.loginId = loginId;
		this.username = username;
		this.password = password;
		this.designation = designation;
	}
	public Login() { 
		super();
	}

}
