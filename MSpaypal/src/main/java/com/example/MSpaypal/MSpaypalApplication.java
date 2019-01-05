package com.example.MSpaypal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MSpaypalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MSpaypalApplication.class, args);
	}

}

