package com.example.MSpaypal.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.example.MSpaypal.service.PayPalService;
import com.fasterxml.jackson.annotation.JsonValue;



@RestController
@RequestMapping(value= "/paypal")
public class PayPalController {

	
	
	 @Autowired
	 private PayPalService payPalService;
	 
	 @Autowired
	 private RestTemplate restTemplate; 
	 
	 

	    @PostMapping(value = "/make/payment")
	    public Map<String, Object> makePayment(@RequestBody PayPalDTO pp){
	    	
	    
	        return payPalService.createPayment(pp);
	    }
	
	
	
	    @JsonValue
	    @PostMapping(value = "/complete/payment")
	    public PayPalResponse completePayment(@RequestBody PaypalConfirmDTO request){
	        return payPalService.completePayment(request);
	    }
	    
	    
	    
}
