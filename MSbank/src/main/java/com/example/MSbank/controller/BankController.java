package com.example.MSbank.controller;

import java.util.Map;

import org.apache.log4j.Logger;
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
import com.example.MSbank.service.BankService;


@RestController
@RequestMapping("/payment")
public class BankController {

	private Logger logger = Logger.getLogger(BankController.class);
	
	@Autowired
	private BankService bankService;
	
	@PostMapping("/bank")
	public String getPaymentO(@RequestBody PaymentObjDTO po) {
		
		logger.info("Method: getPaymentO -> Request: Merchant id= " + po.getMerchantid());
		
		String res = bankService.findbank(po);
		
		
		
		return res;
	}
	
}
