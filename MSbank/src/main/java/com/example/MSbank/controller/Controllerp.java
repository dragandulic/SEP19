package com.example.MSbank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class Controllerp {

	
	
	@GetMapping("/met")
	public String met() {
		System.out.println("dosaoooooooAAA");
		return "papapapapa";
	}
	
}
