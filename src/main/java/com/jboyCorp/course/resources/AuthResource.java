package com.jboyCorp.course.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jboyCorp.course.dto.EmailDTO;
import com.jboyCorp.course.security.JWTUtil;
import com.jboyCorp.course.security.UserSS;
import com.jboyCorp.course.services.AuthService;
import com.jboyCorp.course.services.UserAuthService;

@RestController
@RequestMapping(value= "/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private AuthService service;
	
	@RequestMapping(value= "/refresh_token", method=RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response){
		UserSS userSS = UserAuthService.authenticated();
		String token = jwtUtil.generateToken(userSS.getUsername());
		response.addHeader("Authorization", "Bearer" + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value= "/forgot", method=RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto){
		service.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}

}
