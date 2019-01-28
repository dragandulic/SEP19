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
import com.example.MSbank.repository.PaymentObjDTOReposiotry;



@RestController
@RequestMapping("/bank")
public class BankController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private PaymentObjDTOReposiotry paymentObjDTOReposiotry;
	
	@GetMapping("/getPaymentObj/{id}")
	public String getPaymentO(@PathVariable Long id) {
		
		PaymentObjDTO returnPaymentObj = restTemplate.getForObject("http://localhost:8083/paymentobj/getPaymentObj/" + id, PaymentObjDTO.class);
		
		System.out.println("Dosao u kontroler BANK, cutomer name: " + returnPaymentObj.getNameCustomer());
		
		
		String merchantId = returnPaymentObj.getMerchant_id();
		String merchantPassword = returnPaymentObj.getMerchant_password();
		double amount = returnPaymentObj.getAmount();
		
		Request request = new Request();
		request.setMerchant_id(merchantId);
		request.setMerchant_password(merchantPassword);
		request.setAmount(amount);
		
		org.springframework.http.HttpHeaders header = new org.springframework.http.HttpHeaders();
		
		HttpEntity entity = new HttpEntity(request, header);
		
		String res = restTemplate.postForObject("http://localhost:8097/request/checkrequest", entity, String.class);
		
		if(!res.equals("neuspesno")) {
			paymentObjDTOReposiotry.save(returnPaymentObj);
			String res1 = res + "/id=" + returnPaymentObj.getId();
			System.out.println("RES!!!!!!!!!!!!!!! " + res1);
			return res1;
		}
		
		return res;
	}
	
}
