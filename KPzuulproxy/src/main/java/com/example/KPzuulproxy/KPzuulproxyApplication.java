package com.example.KPzuulproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class KPzuulproxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(KPzuulproxyApplication.class, args);
	}

}

