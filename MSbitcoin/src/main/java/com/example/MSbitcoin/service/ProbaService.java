package com.example.MSbitcoin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MSbitcoin.model.Proba;
import com.example.MSbitcoin.repository.ProbaRepository;

@Service
public class ProbaService {

	@Autowired
	private ProbaRepository probaRepository;
	
	public String dodajProba() {
		
		Proba p = new Proba();
		p.setName("Gagi");
		
		Proba r = probaRepository.save(p);
		
		if(r != null) {
			return r.getName();
		}
		else {
			return "neuspesano!";
		}
	}
	
}
