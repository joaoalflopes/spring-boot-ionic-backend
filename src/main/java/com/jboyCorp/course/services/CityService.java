package com.jboyCorp.course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jboyCorp.course.entities.City;
import com.jboyCorp.course.repositories.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository repo;
	
	public List<City> findByState(Long stateId){
		return repo.findCities(stateId);
	}

}
