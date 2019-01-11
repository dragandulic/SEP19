package com.example.KPzuulproxy.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.KPzuulproxy.dto.KPdto;

@RestController
@RequestMapping("/kpcontroller")
public class KPcontroller {

	
	@PostMapping("/payment")
	public String kpPayment(@RequestBody KPdto o) {
		
		
		System.out.println("POGODILI SMOOOOOOOOOOOOOOOOO");
		return "successful";
		
	}
	
	
	
	
}
