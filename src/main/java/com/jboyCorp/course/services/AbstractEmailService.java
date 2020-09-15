package com.jboyCorp.course.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.jboyCorp.course.entities.Order;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Order obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromOrder(obj);
		sendEmail(sm);
	}

	// Somente acessado por sub-classes, nao pode ser acessado pelos controladores de serviços
	protected SimpleMailMessage prepareSimpleMailMessageFromOrder(Order obj) {
		
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getUser().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Order confirmed! (Request Number: " + obj.getId()+")");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}

}
