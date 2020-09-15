package com.jboyCorp.course.services;

import org.springframework.mail.SimpleMailMessage;

import com.jboyCorp.course.entities.Order;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Order obj);
	
	void sendEmail(SimpleMailMessage msg);

}
