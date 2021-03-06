package com.example.MSbitcoin.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import org.apache.log4j.Logger;


import com.example.MSbitcoin.dto.CreateOrderResponse;
import com.example.MSbitcoin.dto.DataLoaderComponent;
import com.example.MSbitcoin.dto.GetOrder;
import com.example.MSbitcoin.dto.PaymentObjDTO;
import com.example.MSbitcoin.dto.UrlResponse;
import com.example.MSbitcoin.repository.CreateOrderResponseRepository;
import com.example.MSbitcoin.response.PaymentBitcoinResponse;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	private Logger logger = Logger.getLogger(PaymentController.class);
	
	@Value("${eureka.instance.instance-id}")
	private String instanceId;
	
	@Autowired
	private RestTemplate restTemplate; 
	
	@Autowired
	private CreateOrderResponseRepository createOrderResponseRepository;
	
	@Autowired
	private DataLoaderComponent dataLoaderComponent;
	
	@PostMapping(value="/bitcoin")
	public String payment(@RequestBody PaymentObjDTO po){
		
		System.out.println("Hi there! instance id: " + instanceId);
		
		
		Map<String, Object> mapa = new HashMap<>();
		mapa.put("order_id", UUID.randomUUID().toString());
		mapa.put("price_amount", po.getAmount());
		mapa.put("price_currency", "EUR");
		mapa.put("receive_currency", "EUR");
		mapa.put("title", po.getTitle());
		mapa.put("description", po.getDescription());
		mapa.put("callback_url", "https://api-sandbox.coingate.com/account/orders");
		mapa.put("success_url", "http://" + dataLoaderComponent.getIp() + ":3000/bitcoin="+po.getCode());
		
		
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "Token " + po.getBitcointoken());
		HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(mapa, header);
		
		CreateOrderResponse response = restTemplate.postForObject("https://api-sandbox.coingate.com/v2/orders", entity, CreateOrderResponse.class);
		response.setBitcointoken(po.getBitcointoken());
		if(response !=null) {
			logger.info("Method: payment -> Successfuly create order, id=" + response.getId());
		}
		
		CreateOrderResponse c=  createOrderResponseRepository.save(response);
		
		String res = response.getPayment_url() + "," + c.getIdour();
		
		return res;
		
		
	}
	
	@GetMapping(value="/getorder/{id}/{code}")
	public String getOrder(@PathVariable Long id, @PathVariable String code) {
		
		System.out.println("DOSAO U MS");
		CreateOrderResponse cor = createOrderResponseRepository.findByIdourEquals(id);		
	
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "Token " + cor.getBitcointoken());
		
		HttpEntity entity = new HttpEntity(header);
		
		
		int ido = Integer.parseInt(cor.getId());
		
		ResponseEntity<GetOrder> response = restTemplate.exchange("https://api-sandbox.coingate.com/v2/orders/" + cor.getId(),
				HttpMethod.GET, entity, GetOrder.class);
		
		logger.info("Method: getOredr -> Order id=" + response.getBody().getOrder_id());
		
		if(response.getBody()!=null) {
			
			
			Map<String, Object> mapa = new HashMap<>();
			mapa.put("status", response.getBody().getStatus());
			mapa.put("type", "bitcoin");
			mapa.put("currency", response.getBody().getPrice_currency());
			mapa.put("amount", response.getBody().getPrice_amount());
			mapa.put("time", response.getBody().getCreated_at());
			mapa.put("paymentid", response.getBody().getId());
			
			HttpHeaders h = new HttpHeaders();
			
			HttpEntity<Map<String, Object>> e = new HttpEntity<Map<String, Object>>(mapa, h);
			System.out.println("SUCCESSP1");
			UrlResponse re = restTemplate.postForObject("http://" + dataLoaderComponent.getIp() + ":8051/objectpayment/successpayment/" + code, e, UrlResponse.class);
			System.out.println("SUCCESSP2");
			logger.info("Method: getOredr -> Successfuly save transaction");
			return "uspesno";
		}
		
		
		
		return "neuspesno";
	}
	

			//tehnika F5rxiWLazEaQC4yyDyh5nwAReRGUHbRQQevz426J
			//matematicki vesik 1w731yKubAxdtzZix4wRKvrbAYyKeXSs4zf26BPv
			
			/* 1.
			 * gradjevinarstvo TOKEN: dCJnxHQ1f6Q3HQXfJWqoKRgFuDyfMMLeWFw-FNGN
			 * API KEY: tVAdjZqnwefvTWlSBhK40J
			 * API SECRET: BE1OZlpyijP0AMmchJdxL8oYgf56XHIN
			 */
	
			 /* 2.
			  * tehnikovic TOKEN: VYAwg4CCyDxZDyLeRXinbSkJ6DzGbGQwFFK4utH2
			  * API KEY: wbLUSc1WABvDugaEmpn8QO
			  * AOI SECRET: rHS85EtMxnB2ULeFvcb43lJIRPWZQwVf
			  */
			
	 		 /* 3.
	 		  * bankaric TOKEN: 63ia_f8W5zsz3yhZmgomWgJ7bqziwLiiQu9eYAmZ
	 		  * API KEY: QurajGlxOVScFZt39UWK4e
	 		  * API SECRTE: 8KpY0c9HRUhMfOi2noybu1SQxzZkIWr4
	 		  * 
	 		  */
	
}
