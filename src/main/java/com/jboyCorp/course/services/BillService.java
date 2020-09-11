package com.jboyCorp.course.services;

import java.time.Instant;
import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.jboyCorp.course.entities.BankPayment;

@Service
public class BillService {
	
	public void fillPaymentWithBill(BankPayment paid, Instant orderTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(orderTime.toEpochMilli());
		cal.add(Calendar.DAY_OF_MONTH, 7);
		paid.setDueDate(cal.toInstant());
	}

}
