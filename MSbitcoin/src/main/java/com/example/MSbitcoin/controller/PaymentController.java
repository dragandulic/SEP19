package com.example.MSbitcoin.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.MSbitcoin.dto.BitcoinDTO;
import com.example.MSbitcoin.dto.BitcoinResponseDTO;
import com.example.MSbitcoin.dto.PaymentMFDTO;
import com.example.MSbitcoin.response.PaymentBitcoinResponse;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private RestTemplate restTemplate; 
	
	
	@GetMapping(value="/bitcoin/{id}")
	public String payment(@PathVariable Long id){
		System.out.println("CONTROLLLER " );
		String idstring = Long.toString(id);
		PaymentMFDTO responsee = restTemplate.getForObject("http://localhost:8083/paymentobj/getPaymentObj/" + id, PaymentMFDTO.class);      

		
		
		Map<String, Object> mapa = new HashMap<>();
		mapa.put("order_id", UUID.randomUUID().toString());
		mapa.put("price_amount", responsee.getAmount());
		mapa.put("price_currency", "USD");
		mapa.put("receive_currency", "USD");
		mapa.put("title", responsee.getTitle());
		mapa.put("description", responsee.getNameCustomer());
		//mapa.put("callback_url", "...  ");
		//mapa.put("success_url", "...  ");
		
		
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "Token 8W2cFE2hUx55MHxxuisH9gigTzdP7pRjYmQsHH2V");

		HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(mapa, header);
		
		BitcoinResponseDTO response = restTemplate.postForObject("https://api-sandbox.coingate.com/v2/orders", entity, BitcoinResponseDTO.class);
		System.out.println("AAAAAAAAAAAAAAAAAAAAAA " + response.getPayment_url());
		PaymentBitcoinResponse retvalue = new PaymentBitcoinResponse();
		retvalue.setPaymentURL(response.getPayment_url());
		return retvalue.getPaymentURL();
		
		
	}
	
}
