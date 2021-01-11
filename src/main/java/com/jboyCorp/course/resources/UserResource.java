package com.jboyCorp.course.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jboyCorp.course.dto.UserDTO;
import com.jboyCorp.course.dto.UserNewDTO;
import com.jboyCorp.course.entities.User;
import com.jboyCorp.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/email", method = RequestMethod.GET)
	public ResponseEntity<User> find(@RequestParam(value="value")String email){
		User obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@Valid @RequestBody UserNewDTO objDTO) {
		User obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@Valid @RequestBody UserDTO objDTO, @PathVariable Long id){
		User obj = service.fromDTO(objDTO);
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(value = "/page")
	public ResponseEntity<Page<UserDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="direction", defaultValue="ASC")String direction, 
			@RequestParam(value="orderBy", defaultValue="name")String orderBy){
		Page<User> list = service.findPage(page, linesPerPage, direction, orderBy);
		Page<UserDTO> listDto = list.map(obj -> new UserDTO(obj)); 
		return ResponseEntity.ok().body(listDto);
	}
	
	@PostMapping(value = "/picture")
	public ResponseEntity<User> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
		URI uri = service.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}
}
