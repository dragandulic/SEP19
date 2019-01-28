package com.example.MSbank.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.MSbank.dto.PaymentObjDTO;
import com.example.MSbank.model.Request;




@RestController
@RequestMapping("/payment")
public class BankController {

	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("/bank")
	public String getPaymentO(@RequestBody PaymentObjDTO po) {
		
		Request request = new Request();
		request.setMerchant_id(po.getMerchantid());
		request.setMerchant_password(po.getMerchantpassword());
		request.setAmount(po.getAmount());
		
		HttpHeaders header = new HttpHeaders();
		
		HttpEntity entity = new HttpEntity(request, header);
		
		String res = restTemplate.postForObject("http://localhost:8097/request/checkrequest", entity, String.class);
		
		/*
		if(!res.equals("neuspesno")) {
			paymentObjDTOReposiotry.save(returnPaymentObj);
			String res1 = res + "/id=" + returnPaymentObj.getId();
			System.out.println("RES!!!!!!!!!!!!!!! " + res1);
			return res1;
		}
		*/
		return res;
	}
	
}
