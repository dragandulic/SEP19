package com.example.MSbitcoin.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.MSbitcoin.dto.BitcoinDTO;
import com.example.MSbitcoin.dto.BitcoinResponseDTO;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private RestTemplate restTemplate; 
	
	
	@PostMapping(value="/bitcoin")
	public ResponseEntity<?> payment(@RequestBody BitcoinDTO bitcoindto){
		System.out.println("CONTROLLLER");
		
		Map<String, Object> mapa = new HashMap<>();
		mapa.put("order_id", UUID.randomUUID().toString());
		mapa.put("price_amount", bitcoindto.getAmount());
		mapa.put("price_currency", "USD");
		mapa.put("receive_currency", "USD");
		mapa.put("title", bitcoindto.getTitle());
		mapa.put("description", "des");
		//mapa.put("callback_url", "...  ");
		//mapa.put("success_url", "...  ");
		
		
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "Token 8W2cFE2hUx55MHxxuisH9gigTzdP7pRjYmQsHH2V");

		HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(mapa, header);
		
		BitcoinResponseDTO response = restTemplate.postForObject("https://api-sandbox.coingate.com/v2/orders", entity, BitcoinResponseDTO.class);
		System.out.println("AAAAAAAAAAAAAAAAAAAAAA " + response.getPayment_url());
		return null;
	}
	
}
