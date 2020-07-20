package com.order.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import feign.Contract;


@SpringBootApplication
@EnableFeignClients("com.order.service.feignclient")
public class CartOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartOrderApplication.class, args);
	}

	@Bean
	public Contract useFeignAnnotations() {
	    return new Contract.Default();
	}
}
