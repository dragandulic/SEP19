package com.example.KPzuulproxy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.KPzuulproxy.controller.TransactionDTO;
import com.example.KPzuulproxy.model.ObjectPayment;
import com.example.KPzuulproxy.repository.ObjectPaymentRepository;

@Service
public class ObjectPaymentService {

	@Autowired
	private ObjectPaymentRepository objectPaymentRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String savePaymentObject(ObjectPayment ob) {
		
		ObjectPayment objectp = objectPaymentRepository.save(ob);
		
		
		if(objectp != null) {
			return objectp.getCode();	
		}
		
		return null;
		
	}
	
	
	public String getObjBitcoin(String code) {
		
		ObjectPayment objRes = objectPaymentRepository.findOneByCode(code);
		
		if(objRes != null) {
			
			HttpHeaders header = new HttpHeaders();	
			HttpEntity entity = new HttpEntity(objRes, header);
					
			String response = restTemplate.postForObject("http://localhost:8060/payment/bitcoin", entity, String.class);
			
			return response;
		}
		return null;
	}
	
    public String getObjPaypal(String code) {
		
		ObjectPayment objRes = objectPaymentRepository.findOneByCode(code);
		
		if(objRes != null) {
			
			HttpHeaders header = new HttpHeaders();	
			HttpEntity entity = new HttpEntity(objRes, header);
					
			String response = restTemplate.postForObject("http://localhost:8061/paypal/make/payment", entity, String.class);
			
			return response;
		}
		return null;
	}
	
	
	public String getObjBank(String code) {
		
		ObjectPayment objRes = objectPaymentRepository.findOneByCode(code);
		
		if(objRes != null) {
			
			HttpHeaders header = new HttpHeaders();	
			HttpEntity entity = new HttpEntity(objRes, header);
					
			String response = restTemplate.postForObject("http://localhost:8062/payment/bank", entity, String.class);
			
			return response;
		}
		return null;
	}
	
	
	public boolean checkUniqueCode(String cod){
		
		ObjectPayment o= objectPaymentRepository.findOneByCode(cod);
		
		
		if(o==null){
			return true;
		}
		else{
			
			return false;
		}
	}
	
	
	public String successpayment(String code,TransactionDTO t) {
		
		ObjectPayment o = objectPaymentRepository.findOneByCode(code);
		
		if(o!=null) {
			o.setVerified(true);
			o.setDescription(t.getDescription());
			o.setType(t.getType());
			o.setCurrency(t.getCurrency());
			o.setMerchantmail(t.getMerchant());
			o.setPayermail(t.getPayeremail());
			
			objectPaymentRepository.save(o);
			
			HttpHeaders header = new HttpHeaders();	
			HttpEntity entity = new HttpEntity(o, header);
					
			String response = restTemplate.postForObject(o.getSuccessUrl()+"/save/transaction", entity, String.class);
			return "uspesno";
		}
		
		return null;
	}
	
	
	
	
	
}
