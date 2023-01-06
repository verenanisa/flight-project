package com.flight.model;

import jakarta.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = true)
	private boolean logStatus = false;
	@Column(nullable = true)
	private boolean isAdmin = false;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLogStatus() {
		return logStatus;
	}

	public void setLogStatus(boolean logStatus) {
		this.logStatus = logStatus;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password=" + password + ", logStatus=" + logStatus
				+ ", isAdmin=" + isAdmin + "]";
	}

}
