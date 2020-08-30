package com.jboyCorp.course.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.jboyCorp.course.services.validation.UserInsert;

@UserInsert
public class UserNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Field cannot be empty!")
	@Length(min=5, max=100, message="Field accepts between 5 and 100 characters!")
	private String name;
	
	@NotEmpty(message="Field cannot be empty!")
	@Email(message="Invalid e-mail!")
	private String email;
	
	@NotEmpty(message="Field cannot be empty!")
	private String cpfOuCnpj;
	private Integer typeClient;
	
	@NotEmpty(message="Field cannot be empty!")
	private String password;
	
	@NotEmpty(message="Field cannot be empty!")
	private String place;
	
	@NotEmpty(message="Field cannot be empty!")
	private String numberPlace;
	
	private String addressComplement;
	
	@NotEmpty(message="Field cannot be empty!")
	private String neightBorHood;
	
	@NotEmpty(message="Field cannot be empty!")
	private String zipCode;
	
	@NotEmpty(message="Field cannot be empty!")
	private String phone1;
	private String phone2;
	private String phone3;
	
	private Long cityId;
	
	public UserNewDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTypeClient() {
		return typeClient;
	}

	public void setTypeClient(Integer typeClient) {
		this.typeClient = typeClient;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getNumberPlace() {
		return numberPlace;
	}

	public void setNumberPlace(String numberPlace) {
		this.numberPlace = numberPlace;
	}

	public String getAddressComplement() {
		return addressComplement;
	}

	public void setAddressComplement(String addressComplement) {
		this.addressComplement = addressComplement;
	}

	public String getNeightBorHood() {
		return neightBorHood;
	}

	public void setNeightBorHood(String neightBorHood) {
		this.neightBorHood = neightBorHood;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
}
