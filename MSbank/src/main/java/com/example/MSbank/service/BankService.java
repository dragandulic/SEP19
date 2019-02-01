package com.example.MSbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.MSbank.dto.PaymentObjDTO;
import com.example.MSbank.model.Bank;
import com.example.MSbank.model.Request;
import com.example.MSbank.repository.BankRepository;

@Service
public class BankService {

	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String findbank(PaymentObjDTO po) {
		
		
		Request request = new Request();
		request.setMerchant_id(po.getMerchantid());
		request.setMerchant_password(po.getMerchantpassword());
		request.setAmount(po.getAmount());
		request.setSuccessurl("http://localhost:3000/Successfully");
		request.setErrorurl("http://localhost:3000/error");
		request.setFailedurl("http://localhost:3000/failed");
		request.setMerchant_order_id(po.getCode());
		
		String numberOfBank = "";
		
		for(int i =0;i<3;i++) {
			numberOfBank+= po.getMerchantid().charAt(i);
		}
		
		if(!numberOfBank.equals("")) {
			Bank b = bankRepository.findByBanknumberEquals(numberOfBank);
			
			HttpHeaders header = new HttpHeaders();	
			HttpEntity entity = new HttpEntity(request, header);
			
			String res = restTemplate.postForObject(b.getUrl(), entity, String.class);
			
			/*
			if(!res.equals("neuspesno")) {
				
				String res1 = res + "/id=" + po.getMerchantid();
				System.out.println("RES!!!!!!!!!!!!!!! " + res1);
				return res1;
			}
			*/
			return res;
			
		}

		return null;
	}
	
}
