package com.example.MSpaypal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MSpaypal.service.Servicep;

@RestController
@RequestMapping(value= "/paypal")
public class controller {

	@Autowired
	private Servicep servicep;
	
	@GetMapping("/pay")
	public String metoda() {
		System.out.println("Dosaooooooooooo");
		
		String retval = servicep.metoda(); 
		
		return retval;
	}
	
}
