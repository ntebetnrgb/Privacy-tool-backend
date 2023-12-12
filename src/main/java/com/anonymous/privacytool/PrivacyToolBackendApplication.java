package com.anonymous.privacytool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PrivacyToolBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(PrivacyToolBackendApplication.class, args);
	}

	@GetMapping("/healthy")
	public String healthCheck() {
		return "Still breathing!";
	}
}
