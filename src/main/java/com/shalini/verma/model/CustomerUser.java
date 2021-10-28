package com.shalini.verma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class CustomerUser {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkUserId;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="full_name")
	private String fullName;
	public Integer getPkUserId() {
		return pkUserId;
	}
	public void setPkUserId(Integer pkUserId) {
		this.pkUserId = pkUserId;
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
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	

}
