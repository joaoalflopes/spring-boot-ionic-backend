package com.jboyCorp.course.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jboyCorp.course.dto.UserNewDTO;
import com.jboyCorp.course.entities.enums.TypeClient;
import com.jboyCorp.course.resources.exceptions.FieldMessage;
import com.jboyCorp.course.services.validation.utils.BR;

public class UserInsertValidator implements ConstraintValidator<UserInsert, UserNewDTO> {
	@Override
	public void initialize(UserInsert ann) {
	}

	@Override
	public boolean isValid(UserNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista
		
		if(objDTO.getTypeClient().equals(TypeClient.PESSOAFISICA.getCode()) && !BR.isValidCPF(objDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "Invalid CPF."));
		}
		
		if(objDTO.getTypeClient().equals(TypeClient.PESSOAJURIDICA.getCode()) && !BR.isValidCNPJ(objDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("CpfOuCnpj", "Invalid CNPJ."));
		}
		
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
