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



@RestController
@RequestMapping(value= "/paypal")
public class PayPalController {

	
	
	 @Autowired
	 private PayPalService payPalService;
	 
	 @Autowired
	 private RestTemplate restTemplate; 
	 
	 

	    @GetMapping(value = "/make/payment/{id}")
	    public Map<String, Object> makePayment(@PathVariable Long id){
	    	
	    //	System.out.println(id + "ovo je id placanjaa");
	    	
	    	PaymentMFDTO responsee = restTemplate.getForObject("http://localhost:8083/paymentobj/getPaymentObj/" + id, PaymentMFDTO.class);
	        return payPalService.createPayment(responsee);
	    }
	
	
	
	    @PutMapping(value = "/complete/payment")
	    public Map<String, Object> completePayment(@RequestBody PaypalConfirmDTO request){
	        return payPalService.completePayment(request);
	    }
	    
	    
	    
}
