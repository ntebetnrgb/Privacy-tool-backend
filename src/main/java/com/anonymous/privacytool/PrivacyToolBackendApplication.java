package com.anonymous.privacytool;

import com.anonymous.privacytool.config.S3PropertiesLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PrivacyToolBackendApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PrivacyToolBackendApplication.class);
		app.addInitializers(new S3PropertiesLoader());
		app.run(args);
	}

	@GetMapping("/healthy")
	public String healthCheck() {
		return "Still breathing!";
	}

	@GetMapping("/healthyy")
	public String healthCheckk() {
		return "Still breathing!";
	}
}
