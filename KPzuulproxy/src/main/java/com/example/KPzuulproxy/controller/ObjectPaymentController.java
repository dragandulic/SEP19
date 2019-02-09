package com.example.KPzuulproxy.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.KPzuulproxy.dto.DataLoaderComponent;
import com.example.KPzuulproxy.model.ObjectPayment;
import com.example.KPzuulproxy.repository.ObjectPaymentRepository;
import com.example.KPzuulproxy.service.ObjectPaymentService;

@RestController
@RequestMapping("/objectpayment")
public class ObjectPaymentController {

	
	@Autowired
	private ObjectPaymentService objectPaymentService;
	
	
	@Autowired
	private ObjectPaymentRepository objectPaymentRepository;
	
	@Autowired
	private DataLoaderComponent dataLoaderComponent;
	
	
	@PostMapping("/savepaymentobject")
	public String savePaymentObject(@RequestBody ObjectPayment ob){
		
		
		String generated= new String(RandomStringUtils.randomAlphanumeric(10).toUpperCase());
		boolean check= objectPaymentService.checkUniqueCode(generated);
		
		while(check!=true)
		{
			
			generated= new String(RandomStringUtils.randomAlphanumeric(10).toUpperCase());
			 check= objectPaymentService.checkUniqueCode(generated);
		}
		
		
			
		ob.setCode(generated);
		
		
		
		String res = objectPaymentService.savePaymentObject(ob);
		if(ob.getDescription().equals("Membership fee for 30 days")){
			String res1="http://" + dataLoaderComponent.getIp() + ":3000/paypal="+res;
			return res1;
		}
		else if(ob.getTitle().contains("Placanje clanarine")) {
			String res1="http://" + dataLoaderComponent.getIp() + ":3000/paypal="+res;
			return res1;
		}
		else{
		String res1="http://" + dataLoaderComponent.getIp() + ":3000/id="+res;
		return res1;
		}
		
	}
	
	@GetMapping("/getobjectbitcoin/{code}")
	public String getObjectBitcoin(@PathVariable String code) {
		
		String res = objectPaymentService.getObjBitcoin(code);
		
		return res;
		
		
	}
	
	
	@GetMapping("/getobjectbank/{code}")
	public String getObjectBank(@PathVariable String code) {
		
		String res = objectPaymentService.getObjBank(code);
		
		return res;
		
		
	}
	
	@GetMapping("/getobjectpaypal/{code}")
	public String getObjectPaypal(@PathVariable String code) {
		
		String res = objectPaymentService.getObjPaypal(code);
		
		return res;
		
		
	}
	
	@PostMapping("/successpayment/{code}")
	public UrlResponse successPayment(@PathVariable String code, @RequestBody TransactionDTO dto) {
		System.out.println("CODEE: " + code);
		UrlResponse res = objectPaymentService.successpayment(code,dto);
		
		System.out.println("MEDOA KP");
		return res;
	}
	
	@GetMapping("/gotonc/{code}")
	public String goToNc(@PathVariable String code) {
		
		ObjectPayment o = objectPaymentRepository.findOneByCode(code);
		
		if(o!=null) {
			return o.getFronturl();
		}

		return null;
	}
}
