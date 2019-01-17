package com.example.MSpaypal.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MSpaypal.service.PayPalService;



@RestController
@RequestMapping(value= "/paypal")
public class PayPalController {

	
	
	 @Autowired
	 private PayPalService payPalService;
	 
	 
	 
	 

	    @PostMapping(value = "/make/payment")
	    public Map<String, Object> makePayment(@RequestBody PayPalDTO payPalDTO){
	        return payPalService.createPayment(payPalDTO.getSum());
	    }
	
	
	
	    @PostMapping(value = "/complete/payment")
	    public Map<String, Object> completePayment(HttpServletRequest request){
	        return payPalService.completePayment(request);
	    }
	    
	    
	    
}
