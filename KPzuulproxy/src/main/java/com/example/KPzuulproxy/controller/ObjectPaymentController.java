package com.example.KPzuulproxy.controller;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.KPzuulproxy.model.ObjectPayment;
import com.example.KPzuulproxy.service.ObjectPaymentService;

@RestController
@RequestMapping("/objectpayment")
public class ObjectPaymentController {

	
	@Autowired
	private ObjectPaymentService objectPaymentService;
	
	@PostMapping("/savepaymentobject")
	public String savePaymentObject(@RequestBody ObjectPayment ob){
		
		
		String generated= new String(RandomStringUtils.randomAlphanumeric(7).toUpperCase());
		boolean check= objectPaymentService.checkUniqueCode(generated);
		
		while(check!=true)
		{
			
			generated= new String(RandomStringUtils.randomAlphanumeric(7).toUpperCase());
			 check= objectPaymentService.checkUniqueCode(generated);
		}
		
		
			
		ob.setCode(RandomStringUtils.randomAlphanumeric(7).toUpperCase());
		
		
		
		String res = objectPaymentService.savePaymentObject(ob);
		
		
		
		return res;
	}
	
	@GetMapping("/getobjectbitcoin/{ido}")
	public String getObjectBitcoin(@PathVariable Long ido) {
		
		String res = objectPaymentService.getObjBitcoin(ido);
		
		return res;
		
		
	}
	
	
	@GetMapping("/getobjectbank/{ido}")
	public String getObjectBank(@PathVariable Long ido) {
		
		String res = objectPaymentService.getObjBank(ido);
		
		return res;
		
		
	}
	
	@GetMapping("/getobjectpaypal/{code}")
	public String getObjectBitcoin(@PathVariable String code) {
		
		String res = objectPaymentService.getObjPaypal(code);
		
		return res;
		
		
	}
	
}
