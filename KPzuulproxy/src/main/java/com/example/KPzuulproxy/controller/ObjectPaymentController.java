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
import com.example.KPzuulproxy.repository.ObjectPaymentRepository;
import com.example.KPzuulproxy.service.ObjectPaymentService;

@RestController
@RequestMapping("/objectpayment")
public class ObjectPaymentController {

	
	@Autowired
	private ObjectPaymentService objectPaymentService;
	
	

	
	
	
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
		
		String res1="http://localhost:3000/id="+res;
		
		return res1;
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
	
	@GetMapping("/successpayment/{code}")
	public String successPayment(@PathVariable String code, @RequestBody TransactionDTO dto) {
		
		String res = objectPaymentService.successpayment(code,dto);
		

		return res;
	}
	
	
}
