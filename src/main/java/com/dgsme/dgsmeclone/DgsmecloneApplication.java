package com.dgsme.dgsmeclone;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class DgsmecloneApplication {

	public static void main(String[] args) {
		
		 TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		SpringApplication.run(DgsmecloneApplication.class, args);
	}


}
