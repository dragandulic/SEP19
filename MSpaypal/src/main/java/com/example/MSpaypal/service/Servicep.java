package com.example.MSpaypal.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.MSpaypal.model.Modelp;
import com.example.MSpaypal.repository.Repositoryp;

@org.springframework.stereotype.Service
public class Servicep {

	@Autowired
	private Repositoryp repositoryp;
	
	public String metoda() {
		
		Modelp m = new Modelp();
		
		m.setName("Marko");
		m.setSurname("Markovic");
		
		Modelp sav = repositoryp.save(m);
		
		if(sav.getName().equals("Marko")) {
			System.out.println("DDDDDDDDDDDDDDDDDDDDDDDd");
		}
		else {
			System.out.println("NNNNNNNNNNNNNNNNNNNNNNNN");
		}
		
		
		if(sav.getSurname().equals("Nikolic")) {
			System.out.println("DDDDDDDDDDDDDDDDDDDDDDDd");
		}
		else {
			System.out.println("NNNNNNNNNNNNNNNNNNNNNNNN");
		}
		
		return "vratiooooo";
	}
	
}
