package com.example.MSbitcoin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MSbitcoin.service.ProbaService;

@RestController
@RequestMapping("/proba")
public class ProbalController {

	@Autowired
	private ProbaService probaService;
	
	@GetMapping("/ispis")
	public String ispisi() {
		System.out.println("Dosao u probaControlerrrrrrrrrrrrrrrr");
		String retval = probaService.dodajProba();
		return retval;
	}
	
}
