package com.jboyCorp.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jboyCorp.course.entities.Category;
import com.jboyCorp.course.repositories.CategoryRepository;
import com.jboyCorp.course.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category findById(Long id){
		Optional<Category> obj = repository.findById(id);
		if(obj == null) {
			throw new ResourceNotFoundException("Object Not Found! id: " + id
					+ ", Type: " + Category.class.getName());	
		}
		return obj.get();
	}
	
	public Category insert(Category obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Category update(Category obj) {
		findById(obj.getId());
		return repository.save(obj);
	}
}
