package com.jboyCorp.course.dto;

import java.io.Serializable;

import com.jboyCorp.course.entities.State;

public class StateDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	public StateDTO() {
	}
	
	public StateDTO(State obj) {
		setId(obj.getId());
		setName(obj.getName());
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
}
