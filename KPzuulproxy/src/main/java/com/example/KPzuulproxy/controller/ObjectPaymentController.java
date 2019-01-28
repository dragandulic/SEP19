package com.example.KPzuulproxy.controller;

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
	public Long savePaymentObject(@RequestBody ObjectPayment ob) {
		
		
		Long res = objectPaymentService.savePaymentObject(ob);
		
		System.out.println("SACUVAO PAYMENT OBJECT U KP, ID=" + res);
		
		return res;
	}
	
	@GetMapping("/getobjectbitcoin/{ido}")
	public String getObjectBitcoin(@PathVariable Long ido) {
		
		String res = objectPaymentService.getObjBitcoin(ido);
		
		return res;
		
		
	}
	
}
