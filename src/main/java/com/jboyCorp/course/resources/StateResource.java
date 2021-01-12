package com.jboyCorp.course.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jboyCorp.course.dto.CityDTO;
import com.jboyCorp.course.dto.StateDTO;
import com.jboyCorp.course.entities.City;
import com.jboyCorp.course.entities.State;
import com.jboyCorp.course.services.CityService;
import com.jboyCorp.course.services.StateService;

@RestController
@RequestMapping(value="/states")
public class StateResource {
	
	@Autowired
	private StateService service;
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StateDTO>> findAll(){
		List<State> list = service.findAll();
		List<StateDTO> listDTO = list.stream().map(obj -> new StateDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/{stateId}/cities", method = RequestMethod.GET)
	public ResponseEntity<List<CityDTO>> findCities(@PathVariable Long stateId){
		List<City> list = cityService.findByState(stateId);
		List<CityDTO> listDTO = list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	

}
