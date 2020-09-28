package com.jboyCorp.course.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jboyCorp.course.entities.User;
import com.jboyCorp.course.repositories.UserRepository;
import com.jboyCorp.course.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder pen;
	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	public void sendNewPassword(String email) {

		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new ObjectNotFoundException("Email not found");
		}

		String newPass = newPassword();
		user.setPassword(pen.encode(newPass));

		userRepository.save(user);
		emailService.sendNewPasswordEmail(user, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) {// command generates a number
			return (char) (rand.nextInt(10) + 48);

		} else if (opt == 1) {// command generates an uppercase alphabetic digit
			return (char) (rand.nextInt(26) + 65);
		} else {// command generates an lowercase alphabetic digit
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
