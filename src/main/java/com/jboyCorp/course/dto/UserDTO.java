package com.jboyCorp.course.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.jboyCorp.course.entities.User;
import com.jboyCorp.course.services.validation.UserUpdate;

@UserUpdate
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message="Field cannot be empty!")
	@Length(min=5, max=100, message="Field accepts between 5 and 100 characters!")
	private String name;
	
	@NotEmpty(message="Field cannot be empty!")
	@Email(message="Invalid e-mail!")
	private String email;
	
		
	
	

	public UserDTO() {
	}
	
	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
