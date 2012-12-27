package com.me.src.pojo;


import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class UserAccount extends MappedModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	
	private String password;
	
	private String role;

	
	@OneToOne
	private Person person;
	
	public UserAccount() {
		//person = new Person();
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
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
