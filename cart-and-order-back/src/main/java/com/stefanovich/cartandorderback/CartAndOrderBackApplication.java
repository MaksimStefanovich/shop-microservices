package com.stefanovich.cartandorderback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CartAndOrderBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartAndOrderBackApplication.class, args);
	}

}
