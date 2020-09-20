package com.jboyCorp.course.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jboyCorp.course.entities.BankPayment;
import com.jboyCorp.course.entities.Order;
import com.jboyCorp.course.entities.OrderItem;
import com.jboyCorp.course.entities.enums.OrderStatus;
import com.jboyCorp.course.repositories.OrderItemRepository;
import com.jboyCorp.course.repositories.OrderRepository;
import com.jboyCorp.course.repositories.PaymentRepository;
import com.jboyCorp.course.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	@Autowired
	private BillService billService;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;
	
	
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	@Transactional
	public Order insert(Order obj) {
		obj.setId(null);
		obj.setMoment(Instant.now());
		obj.setUser(userService.findById(obj.getUser().getId()));
		obj.setOrderStatus(OrderStatus.WAITING_PAYMENT);
		obj.getPayment().setOrder(obj);
		if(obj.getPayment() instanceof BankPayment) {
			BankPayment paid = (BankPayment) obj.getPayment();
			billService.fillPaymentWithBill(paid, obj.getMoment());
			
		}
		obj = repository.save(obj);
		paymentRepository.save(obj.getPayment());
		for(OrderItem oI : obj.getItems()) {
			oI.setProduct(productService.findById(oI.getProduct().getId()));
			oI.setPrice(oI.getProduct().getPrice());
			oI.setOrder(obj);
		}
		orderItemRepository.saveAll(obj.getItems());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
}
