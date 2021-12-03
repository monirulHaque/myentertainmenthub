package com.monirul.myentertainmenthub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MyentertainmenthubApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyentertainmenthubApplication.class, args);
	}

	@GetMapping
	public String kichu() {
		return "<h1>Shihab Uddin</h1>";
	}
}
