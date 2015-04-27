package com.grzk.elearning.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class UserRegisterRequest {
	
	@NotNull
	private String username;
	
	@Email
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	private String passwordConfirm;
	
	@AssertTrue(message="{validation.thesame}")
	private boolean isPassword(){
		return password.equals(passwordConfirm);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}
