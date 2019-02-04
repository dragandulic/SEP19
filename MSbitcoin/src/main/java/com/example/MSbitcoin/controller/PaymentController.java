package com.example.MSbitcoin.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.MSbitcoin.dto.CreateOrderResponse;
import com.example.MSbitcoin.dto.GetOrder;
import com.example.MSbitcoin.dto.PaymentObjDTO;
import com.example.MSbitcoin.repository.CreateOrderResponseRepository;
import com.example.MSbitcoin.response.PaymentBitcoinResponse;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private RestTemplate restTemplate; 
	
	@Autowired
	private CreateOrderResponseRepository createOrderResponseRepository;
	
	@PostMapping(value="/bitcoin")
	public String payment(@RequestBody PaymentObjDTO po){
		
		Map<String, Object> mapa = new HashMap<>();
		mapa.put("order_id", UUID.randomUUID().toString());
		mapa.put("price_amount", po.getAmount());
		mapa.put("price_currency", "EUR");
		mapa.put("receive_currency", "EUR");
		mapa.put("title", po.getTitle());
		mapa.put("description", po.getDescription());
		mapa.put("callback_url", "https://api-sandbox.coingate.com/account/orders");
		mapa.put("success_url", "http://localhost:3000/bitcoin="+po.getCode());
		
		
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "Token 1w731yKubAxdtzZix4wRKvrbAYyKeXSs4zf26BPv");
		HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(mapa, header);
		
		CreateOrderResponse response = restTemplate.postForObject("https://api-sandbox.coingate.com/v2/orders", entity, CreateOrderResponse.class);
		CreateOrderResponse c=  createOrderResponseRepository.save(response);

		String res = response.getPayment_url() + "," + c.getIdour();
		
		return res;
		
		
	}
	
	@GetMapping(value="/getorder/{id}/{code}")
	public String getOrder(@PathVariable Long id, @PathVariable String code) {
		
		System.out.println("Dosao u metoduuuuuuuuuu " + id);
		CreateOrderResponse cor = createOrderResponseRepository.findByIdourEquals(id);		
		
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "Token 1w731yKubAxdtzZix4wRKvrbAYyKeXSs4zf26BPv");
		
		HttpEntity entity = new HttpEntity(header);
		

		int ido = Integer.parseInt(cor.getId());
		System.out.println("https://api-sandbox.coingate.com/v2/orders/" + cor.getId());
		ResponseEntity<GetOrder> response = restTemplate.exchange("https://api-sandbox.coingate.com/v2/orders/" + cor.getId(),
				HttpMethod.GET, entity, GetOrder.class);
		
		
		
		if(response.getBody()!=null) {
			
			
			Map<String, Object> mapa = new HashMap<>();
			mapa.put("status", response.getBody().getStatus());
			mapa.put("type", "bitcoin");
			mapa.put("currency", response.getBody().getPay_currency());
			mapa.put("amount", response.getBody().getPrice_amount());
			mapa.put("time", response.getBody().getCreated_at());
			mapa.put("paymentid", response.getBody().getId());
			
			HttpHeaders h = new HttpHeaders();
			
			HttpEntity<Map<String, Object>> e = new HttpEntity<Map<String, Object>>(mapa, h);
			
			String re = restTemplate.postForObject("http://localhost:8051/objectpayment/successpayment/" + code, e, String.class);
			return "uspesno";
		}
		
		
		
		return "neuspesno";
	}
	
	
	
}
