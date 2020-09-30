package com.jboyCorp.course.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jboyCorp.course.dto.UserDTO;
import com.jboyCorp.course.dto.UserNewDTO;
import com.jboyCorp.course.entities.Address;
import com.jboyCorp.course.entities.City;
import com.jboyCorp.course.entities.User;
import com.jboyCorp.course.entities.enums.TypeClient;
import com.jboyCorp.course.entities.enums.UserProfile;
import com.jboyCorp.course.repositories.AddressRepository;
import com.jboyCorp.course.repositories.UserRepository;
import com.jboyCorp.course.security.UserSS;
import com.jboyCorp.course.services.exceptions.AuthorizationException;
import com.jboyCorp.course.services.exceptions.DataBaseException;
import com.jboyCorp.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private BCryptPasswordEncoder pen;
	
	@Autowired
	private UserRepository repository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private S3Service s3Service;

	
	public List<User> findAll() {
		return repository.findAll();
	}

	public Page<User> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public User findById(Long id) {
		
		UserSS user = UserAuthService.authenticated();
		if(user==null || !user.hasRole(UserProfile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Access denied");
		}
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Transactional
	public User insert(User obj) {
		obj.setId(null);
		obj = repository.save(obj);
		addressRepository.saveAll(obj.getAdresses());
		return obj;
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	public User update(Long id, User obj) {
		User entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
						
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		return s3Service.uploadFile(multipartFile);
	}
	

	// Auxiliary Method 1
	public User fromDTO(UserDTO objDTO) {
		User user = new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), null, null, null);
		return user;
	}
		

	// Auxiliary Method 2 (Insert)
	public User fromDTO(UserNewDTO objDTO) {
		User user = new User(null, objDTO.getName(), objDTO.getEmail(), objDTO.getCpfOuCnpj(),TypeClient.valueOf(objDTO.getTypeClient()), pen.encode(objDTO.getPassword()));
		City city = new City(objDTO.getCityId(), null, null);
		Address addrs = new Address(null, objDTO.getPlace(), objDTO.getNumberPlace(), objDTO.getAddressComplement(), objDTO.getNeightBorHood(), objDTO.getZipCode(), user, city);
		user.getAdresses().add(addrs);
		user.getPhones().add(objDTO.getPhone1());
		if(objDTO.getPhone2()!=null) {
			user.getPhones().add(objDTO.getPhone2());
		}
		if(objDTO.getPhone3()!=null) {
			user.getPhones().add(objDTO.getPhone3());
		}
		return user;
	}
}
