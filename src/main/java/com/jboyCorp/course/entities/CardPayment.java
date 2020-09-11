package com.jboyCorp.course.entities;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("CardPayment")
public class CardPayment extends Payment {
	private static final long serialVersionUID = 1L;
	
	private Integer installments;
	
	public CardPayment() {
	}

	public CardPayment(Long id, Order order, Integer installments) {
		super(id, order);
		this.installments = installments;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}
}
