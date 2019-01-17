package com.example.MSbank.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.MSbank.dto.PaymentObjDTO;
import com.example.MSbank.model.Request;



@RestController
@RequestMapping("/bank")
public class BankController {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/getPaymentObj/{id}")
	public void getPaymentO(@PathVariable Long id) {
		
		System.out.println(id);
		PaymentObjDTO returnPaymentObj = restTemplate.getForObject("http://localhost:8083/paymentobj/getPaymentObj/" + id, PaymentObjDTO.class);
		
		System.out.println(returnPaymentObj.getNameCustomer());
		System.out.println(returnPaymentObj.getTitle());
		
		String merchantId = returnPaymentObj.getMerchant_id();
		String merchantPassword = returnPaymentObj.getMerchant_password();
		double amountMag = returnPaymentObj.getAmountMag();
		double amount = returnPaymentObj.getAmount();
		
		Request request = new Request();
		request.setMerchant_id(merchantId);
		request.setMerchant_password(merchantPassword);
		request.setAmountMag(amountMag);
		request.setAmount(amount);
		
		org.springframework.http.HttpHeaders header = new org.springframework.http.HttpHeaders();
		
		HttpEntity entity = new HttpEntity(request, header);
		
		restTemplate.postForObject("localhost:8097/request/checkrequest", entity, Request.class);
		
		
	}
	
}
