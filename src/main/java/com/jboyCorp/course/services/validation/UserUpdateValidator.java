package com.jboyCorp.course.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.jboyCorp.course.dto.UserDTO;
import com.jboyCorp.course.entities.User;
import com.jboyCorp.course.repositories.UserRepository;
import com.jboyCorp.course.resources.exceptions.FieldMessage;

public class UserUpdateValidator implements ConstraintValidator<UserUpdate, UserDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public void initialize(UserUpdate ann) {
	}

	@Override
	public boolean isValid(UserDTO objDTO, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = Long.parseLong(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista

		User aux = repository.findByEmail(objDTO.getEmail());
		if(aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email is registered in the database"));
		}
		
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
