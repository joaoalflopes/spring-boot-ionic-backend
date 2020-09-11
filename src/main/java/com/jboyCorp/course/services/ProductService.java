package com.jboyCorp.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jboyCorp.course.dto.ProductDTO;
import com.jboyCorp.course.entities.Category;
import com.jboyCorp.course.entities.Product;
import com.jboyCorp.course.repositories.CategoryRepository;
import com.jboyCorp.course.repositories.ProductRepository;
import com.jboyCorp.course.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Page<Product> search(String name, List<Long> ids,Integer page, Integer linesPerPage, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAllById(ids);
		return repository.findDistinctBynameContainingAndCategoriesIn(name, categories, pageRequest);
	}
	
	public Product update(Long id, Product obj) {
		Product entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}
	
	private void updateData(Product entity, Product obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setPrice(obj.getPrice());
		entity.setImgUrl(obj.getImgUrl());
	}
	
	//Auxiliary Method
	public Product fromDTO(ProductDTO objDTO) {
		Product prod = new Product(null, objDTO.getName(), objDTO.getDescription(), objDTO.getPrice(), objDTO.getImgUrl());
		return prod;		
	}
}
