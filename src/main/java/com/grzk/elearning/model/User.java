package com.grzk.elearning.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table (name="users")
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true,nullable=false)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@Transient
	private String passwordConfirm;
	
	@Column(unique=true,nullable=false)
	private String email;
	
	@Column(nullable=false)
	private boolean enabled = false;
	
	@OneToMany(orphanRemoval=true,cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private List<Authority> authorities;
	
	public void resetAuthorities(){
		authorities = new ArrayList<Authority>();
	}
	
	public void addAuthority(Authority authority){
		authorities.add(authority);
		authority.setUser(this);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}
