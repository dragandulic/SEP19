package com.example.KPzuulproxy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.KPzuulproxy.controller.TransactionDTO;
import com.example.KPzuulproxy.controller.UrlResponse;
import com.example.KPzuulproxy.dto.DataLoaderComponent;
import com.example.KPzuulproxy.dto.RestemplateLoadBalanced;
import com.example.KPzuulproxy.model.ObjectPayment;
import com.example.KPzuulproxy.repository.ObjectPaymentRepository;

@Service
public class ObjectPaymentService {

	
	
	@Autowired
    private RestTemplate restTemplate;

    @Autowired
    @LoadBalanced
    private RestTemplate loadBalanced;

    /*
    public String doOtherStuff() {
        return loadBalanced.getForObject("http://stores/stores", String.class);
    }

    public String doStuff() {
        return restTemplate.getForObject("http://example.com", String.class);
    }
	*/
	
	
	@Autowired
	private ObjectPaymentRepository objectPaymentRepository;
	
	@Autowired
	private DataLoaderComponent dataLoaderComponent;
	

	
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
			
			
			
			//String response = restTemplate.postForObject("http://" + dataLoaderComponent.getIp() + ":8060/payment/bitcoin", entity, String.class);
			String response = loadBalanced.postForObject("http://ms-bitcoin/payment/bitcoin", entity, String.class);
			return response;
		}
		return null;
	}
	
    public String getObjPaypal(String code) {
		  
		ObjectPayment objRes = objectPaymentRepository.findOneByCode(code);
		
		if(objRes != null) {
			
			HttpHeaders header = new HttpHeaders();	
			HttpEntity entity = new HttpEntity(objRes, header);
					
			String response = loadBalanced.postForObject("http://ms-paypal/paypal/make/payment", entity, String.class);
			
			return response;
		}
		return null;
	}
	
	
	public String getObjBank(String code) {
		
		ObjectPayment objRes = objectPaymentRepository.findOneByCode(code);
		
		if(objRes != null) {
			
			HttpHeaders header = new HttpHeaders();	
			HttpEntity entity = new HttpEntity(objRes, header);
					
			//String response = restTemplate.postForObject("http://" + dataLoaderComponent.getIp() + ":8062/payment/bank", entity, String.class);
			String response = loadBalanced.postForObject("http://ms-bank/payment/bank", entity, String.class);
			
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
	
	
	public UrlResponse successpayment(String code,TransactionDTO t) {
		
		
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
				//o.setPayermail(t.getPayeremail());
				o.setAmount(Double.parseDouble(t.getAmount())); //ja izvucem amount kao string
				o.setDatetime(t.getTime());
				o.setType(t.getType());
				o.setPaymentid(t.getPaymentid()); // mozes izvuci id od placanja (npr u bitcoinu)cisto da imamo broj racuna koji je tamo reg.
				
				objectPaymentRepository.save(o);
			}

			
			
			HttpHeaders header = new HttpHeaders();	
			HttpEntity entity = new HttpEntity(o, header);
					
			UrlResponse resp=new UrlResponse();
			System.out.println("PREEEEEEEEE " + o.getSuccessUrl());
			String response = restTemplate.postForObject(o.getSuccessUrl(), entity, String.class);
			System.out.println("POSLEEEEE");
			
			String returnurl=new String(o.getFronturl());
			System.out.println(returnurl + " OVO JE URLLL");
			resp.setFronturl(returnurl);
			return resp;
		}
		
		return null;
	}
	
	
	
	
	
}
