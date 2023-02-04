package com.hackaton.project;

import com.hackaton.project.dtos.JwtParseDTO;
import com.hackaton.project.dtos.UserAuthDTO;
import com.hackaton.project.exceptions.jwts.JWTInvalidException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@SpringBootApplication
@RestController
public class HackMarathonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackMarathonApplication.class, args);
	}

	@GetMapping("/me")
	public Object jwtDetails(HttpServletRequest request) {
		boolean isAuthenticated = Objects.nonNull(request.getAttribute("isAuthenticated"));

		if (!isAuthenticated) {
			throw new JWTInvalidException();
		}

		UserAuthDTO userAuthDTO = (UserAuthDTO) request.getAttribute("user");
		String token = (String) request.getAttribute("token");

		return new JwtParseDTO(token, userAuthDTO);
	}
}
