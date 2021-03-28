package com.shops.suppliers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SuppliersApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuppliersApplication.class, args);
	}

}
