package com.example.MSbank.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.MSbank.controller.BankController;
import com.example.MSbank.dto.DataLoaderComponent;
import com.example.MSbank.dto.PaymentObjDTO;
import com.example.MSbank.model.Bank;
import com.example.MSbank.model.Request;
import com.example.MSbank.repository.BankRepository;

@Service
public class BankService {

	private Logger logger = Logger.getLogger(BankService.class);
	
	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DataLoaderComponent dataLoaderComponent;
	
	public String findbank(PaymentObjDTO po) {
		
		
		Request request = new Request();
		request.setMerchant_id(po.getMerchantid());
		request.setMerchant_password(po.getMerchantpassword());
		request.setAmount(po.getAmount());
		request.setSuccessurl("http://" + dataLoaderComponent.getIp() + ":3000/Successfully");
		request.setErrorurl("http://" + dataLoaderComponent.getIp() + ":3000/error");
		request.setFailedurl("http://" + dataLoaderComponent.getIp() + ":3000/failed");
		request.setMerchant_order_id(po.getCode());
		
		String numberOfBank = "";
		
		for(int i =0;i<3;i++) {
			numberOfBank+= po.getMerchantid().charAt(i);
		}
		
		if(!numberOfBank.equals("")) {
			Bank b = bankRepository.findByBanknumberEquals(numberOfBank);
			logger.info("Method: findbank -> The merchants bank '"+ b.getName()+"' was found successfully");
			HttpHeaders header = new HttpHeaders();	
			HttpEntity entity = new HttpEntity(request, header);
			
			System.out.println("DOSAOOOOOOOOOOOOOOOOO: " + "http://" + dataLoaderComponent.getIp() + b.getUrl());
			
			String res = restTemplate.postForObject("http://" + dataLoaderComponent.getIp() + b.getUrl(), entity, String.class);
			
			
			return res;
			
		}
		else {
			logger.error("Method: findbank -> The merchants bank  was not found");
		}

		return null;
	}
	
}
