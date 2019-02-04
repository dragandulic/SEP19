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
			System.out.println("Responseeeee " + response);
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
		
			if(t.getType().equals("bitcoin") && t.getStatus().equals("paid")) {
				o.setVerified(true);
				o.setType(t.getType());
				o.setCurrency(t.getCurrency());
				o.setAmount(Double.parseDouble(t.getAmount()));
				o.setDatetime(t.getTime());
				o.setPaymentid(t.getPaymentid());
				
				objectPaymentRepository.save(o);
			}else if(t.getType().equals("bank") && t.getStatus().equals("paid")) {
				o.setVerified(true);
				o.setType(t.getType());
				o.setCurrency(t.getCurrency());
				o.setDatetime(t.getTime());
				
				objectPaymentRepository.save(o);
			}
			else {
				o.setVerified(true);
				o.setDescription(t.getDescription());
				o.setType(t.getType());
				o.setCurrency(t.getCurrency());
				o.setMerchantmail(t.getMerchant());
				o.setPayermail(t.getPayeremail());
				o.setAmount(Double.parseDouble(t.getAmount())); //ja izvucem amount kao string
				o.setDatetime(t.getTime());
				o.setType(t.getType());
				o.setPaymentid(t.getPaymentid()); // mozes izvuci id od placanja (npr u bitcoinu)cisto da imamo broj racuna koji je tamo reg.
				
				objectPaymentRepository.save(o);
			}

			
			
			HttpHeaders header = new HttpHeaders();	
			HttpEntity entity = new HttpEntity(o, header);
					
			String response = restTemplate.postForObject(o.getSuccessUrl(), entity, String.class);
			return "uspesno";
		}
		
		return null;
	}
	
	
	
	
	
}
