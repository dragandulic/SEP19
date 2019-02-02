package com.example.MSbitcoin.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.MSbitcoin.dto.BitcoinResponseDTO;
import com.example.MSbitcoin.dto.PaymentObjDTO;
import com.example.MSbitcoin.response.PaymentBitcoinResponse;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private RestTemplate restTemplate; 
	
	@PostMapping(value="/bitcoin")
	public String payment(@RequestBody PaymentObjDTO po){
		
		Map<String, Object> mapa = new HashMap<>();
		mapa.put("order_id", UUID.randomUUID().toString());
		mapa.put("price_amount", po.getAmount());
		mapa.put("price_currency", "USD");
		mapa.put("receive_currency", "USD");
		mapa.put("title", po.getTitle());
		mapa.put("description", po.getDescription());
		mapa.put("callback_url", "https://api-sandbox.coingate.com/account/orders");
		mapa.put("success_url", "http://localhost:3000/bitcoin="+po.getCode());
		
		
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "Token gAm4KxdZd4btLWZ2s_zsf9G8LEPmiE4RbNxqeDmV");
		HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(mapa, header);
		
		BitcoinResponseDTO response = restTemplate.postForObject("https://api-sandbox.coingate.com/v2/orders", entity, BitcoinResponseDTO.class);
		
		PaymentBitcoinResponse retvalue = new PaymentBitcoinResponse();
		retvalue.setPaymentURL(response.getPayment_url());
		return retvalue.getPaymentURL();
		
		
	}
	
}
