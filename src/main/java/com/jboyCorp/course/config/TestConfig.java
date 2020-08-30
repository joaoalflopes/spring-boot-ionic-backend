package com.jboyCorp.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jboyCorp.course.entities.Address;
import com.jboyCorp.course.entities.Category;
import com.jboyCorp.course.entities.City;
import com.jboyCorp.course.entities.Order;
import com.jboyCorp.course.entities.OrderItem;
import com.jboyCorp.course.entities.Payment;
import com.jboyCorp.course.entities.Product;
import com.jboyCorp.course.entities.State;
import com.jboyCorp.course.entities.User;
import com.jboyCorp.course.entities.enums.OrderStatus;
import com.jboyCorp.course.entities.enums.TypeClient;
import com.jboyCorp.course.repositories.AddressRepository;
import com.jboyCorp.course.repositories.CategoryRepository;
import com.jboyCorp.course.repositories.CityRepository;
import com.jboyCorp.course.repositories.OrderItemRepository;
import com.jboyCorp.course.repositories.OrderRepository;
import com.jboyCorp.course.repositories.ProductRepository;
import com.jboyCorp.course.repositories.StateRepository;
import com.jboyCorp.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Computers");
		Category cat2 = new Category(null, "Girls' Fashion");
		Category cat3 = new Category(null, "Boys' Fashion");
		Category cat4 = new Category(null, "Electronics");
		Category cat5 = new Category(null, "Software");
		Category cat6 = new Category(null, "Arts & Crafts");
		Category cat7 = new Category(null, "Pet Supplies");
		Category cat8 = new Category(null, "Video Games");
		Category cat9 = new Category(null, "Home & Kitchen");
		Category cat10 = new Category(null, "Automotive");
		
		Product p1 = new Product(null, "Mouse Wireless", "Jelly Comb 2.4G Slim Wireless Mouse with Nano Receiver", 11.99, null);
		Product p2 = new Product(null, "Headset with Microphone", "Mpow HC6 USB Headset with Microphone, Comfort-fit Office Computer Headphone", 36.99, null);
		Product p3 = new Product(null, "Samsung Galaxy Buds+ Plus", "Samsung Galaxy Buds+ Plus, True Wireless Earbuds (Wireless Charging Case included)", 149.99, null);
		Product p4 = new Product(null, "Car Vacuum", "HOTOR Corded Car Vacuum Cleaner High Power for Quick Car Cleaning, DC 12V", 25.99, null);
		Product p5 = new Product(null, "ZippyPaws", "ZippyPaws - Skinny Peltz No Stuffing Squeaky Plush Dog Toy", 9.80, null);
		Product p6 = new Product(null, "Kaspersky Internet Security 2020", "Kaspersky Internet Security 2020,3 Devices,1 Year,PC/Mac/Android", 36.99, null); 

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
		
		p1.getCategories().add(cat1);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat4);
		p3.getCategories().add(cat1);
		p3.getCategories().add(cat4);
		p4.getCategories().add(cat10);
		p5.getCategories().add(cat7);
		p6.getCategories().add(cat5);
			
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));
		
		State st1 = new State(null, "Sao Paulo", "SP");
		State st2 = new State(null, "Rio de Janeiro", "RJ");
		State st3 = new State(null, "Minas Gerais", "MG");

		City cit1 = new City(null, "Santos", st1);
		City cit2 = new City(null, "Campinas", st1);
		City cit3 = new City(null, "Petropolis", st2);
		City cit4 = new City(null, "Belo Horizonte", st3);
		City cit5 = new City(null, "Angra dos Reis", st2);
		City cit6 = new City(null, "Poços de Calda", st3);
		City cit7 = new City(null, "São Vicente", st1);

		st1.getCities().addAll(Arrays.asList(cit1, cit2));
		st2.getCities().addAll(Arrays.asList(cit3, cit5));
		st3.getCities().addAll(Arrays.asList(cit4, cit6));
		
		stateRepository.saveAll(Arrays.asList(st1, st2, st3));
		cityRepository.saveAll(Arrays.asList(cit1, cit2, cit3, cit4, cit5, cit6, cit7));
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com","08769488835",TypeClient.PESSOAFISICA ,"123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com","07012742845",TypeClient.PESSOAFISICA ,"123456");
		User u3 = new User(null, "Simoes & Barreira SC Ltda", "simoesbarra@gmail.com", "05755751000131",TypeClient.PESSOAJURIDICA, "445698");
		
		u1.getPhones().addAll(Arrays.asList("13991138797", "1332281406"));
		u2.getPhones().addAll(Arrays.asList("13991236336", "13981328480"));
		u3.getPhones().addAll(Arrays.asList("1332586614", "1332583250"));
	
		Address addrs1 = new Address(null, "Rua Anadir Dias de Carvalho", "586", "Casa", "Joquei Clube", "11450-515",
				u1, cit7);
		Address addrs2 = new Address(null, "Rua Governador Pedro de Toledo", "56", "Apto 95", "Boqueirao", "11045-550",
				u2, cit1);
		Address addrs3 = new Address(null, "Rua Anadir Dias de Carvalho", "586", "-", "Joquei Clube", "11450-515", u2,
				cit7);
		Address addrs4 = new Address(null, "Avenida Dona Ana Costa", "413", "Conj.45", "Gonzaga", "11230-101", u3,
				cit1);
		
		u1.getAdresses().addAll(Arrays.asList(addrs1));
		u2.getAdresses().addAll(Arrays.asList(addrs2, addrs3));
		u3.getAdresses().addAll(Arrays.asList(addrs4));
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		addressRepository.saveAll(Arrays.asList(addrs1, addrs2, addrs3, addrs4));
		
		Order ord1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order ord2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.SHIPPED, u2);
		Order ord3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.DELIVERED, u1);
		Order ord4 = new Order(null, Instant.parse("2019-08-10T16:06:45Z"), OrderStatus.PAID, u3);
		
		orderRepository.saveAll(Arrays.asList(ord1, ord2, ord3, ord4));
		
		OrderItem ordI1 = new OrderItem(ord1, p1, 2, p1.getPrice());
		OrderItem ordI2 = new OrderItem(ord1, p3, 1, p3.getPrice());
		OrderItem ordI3 = new OrderItem(ord2, p3, 2, p3.getPrice());
		OrderItem ordI4 = new OrderItem(ord3, p5, 2, p5.getPrice());
		OrderItem ordI5 = new OrderItem(ord4, p6, 3, p6.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(ordI1, ordI2, ordI3, ordI4, ordI5));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T22:53:07Z") , ord1);
		ord1.setPayment(pay1);
		
		Payment pay2 = new Payment(null, Instant.parse("2019-07-22T16:21:22Z"), ord3);
		ord3.setPayment(pay2);
		
		Payment pay3 = new Payment(null, Instant.parse("2019-07-23T18:25:49Z"), ord2);
		ord2.setPayment(pay3);
		
		Payment pay4 = new Payment(null, Instant.parse("2019-08-11T08:33:15Z"), ord4);
		ord4.setPayment(pay4);
		
		orderRepository.saveAll(Arrays.asList(ord1, ord3, ord2, ord4));
		
	}

}
