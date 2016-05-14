package com.veda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VedaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VedaApplication.class, args);
	}
}
