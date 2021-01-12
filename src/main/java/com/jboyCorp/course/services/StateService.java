package com.jboyCorp.course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jboyCorp.course.entities.State;
import com.jboyCorp.course.repositories.StateRepository;

@Service
public class StateService {
	
	@Autowired
	private StateRepository repo;
	
	public List<State> findAll(){
		return repo.findAllByOrderByName();
	}
}
