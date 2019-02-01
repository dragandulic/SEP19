package com.example.MSpaypal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.example.MSpaypal.controller.PayPalDTO;
import com.example.MSpaypal.controller.PaymentMFDTO;
import com.example.MSpaypal.controller.PaypalConfirmDTO;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PayPalService {
	
	
	
	public Map<String, Object> createPayment(PayPalDTO dto){
	    Map<String, Object> response = new HashMap<String, Object>();
	    Amount amount = new Amount();
	    amount.setCurrency("USD");
	    String price=String.valueOf(dto.getAmount());
	    amount.setTotal(price);
	    Transaction transaction = new Transaction();
	    transaction.setAmount(amount);
	    transaction.setDescription("Membership for 30 days payment");
	    List<Transaction> transactions = new ArrayList<Transaction>();
	    transactions.add(transaction);

	    Payer payer = new Payer();
	    payer.setPaymentMethod("paypal");

	    Payment payment = new Payment();
	    payment.setIntent("sale");
	    payment.setPayer(payer);
	    payment.setTransactions(transactions);

	    RedirectUrls redirectUrls = new RedirectUrls();
	    redirectUrls.setCancelUrl(dto.getSuccessUrl());
	    redirectUrls.setReturnUrl(dto.getSuccessUrl()+"/success");
	    
	    payment.setRedirectUrls(redirectUrls);
	    Payment createdPayment;
	    try {
	        String redirectUrl = "";
	        APIContext context = new APIContext(dto.getClientId(), dto.getClientSecret(), "sandbox");
	        createdPayment = payment.create(context);
	        if(createdPayment!=null){
	            List<Links> links = createdPayment.getLinks();
	            for (Links link:links) {
	                if(link.getRel().equals("approval_url")){
	                    redirectUrl = link.getHref();
	                    break;
	                }
	            }
	            response.put("status", "success");
	            response.put("redirect_url", redirectUrl);
	            System.out.println(redirectUrl);
	        }
	    } catch (PayPalRESTException e) {
	        System.out.println("Error happened during payment creation!");
	    }
	    
	    
	    return response;
	}
	
	
	
	public Map<String, Object> completePayment(PaypalConfirmDTO req){
		
		System.out.println(req.getPaymentId()+" Ovo je id od transakcije");
		System.out.println(req.getPayerID()+ " Ovoje id kupcaaaaa");
	    Map<String, Object> response = new HashMap<String, Object>();
	    Payment payment = new Payment();
	    payment.setId(req.getPaymentId());
	    

	    PaymentExecution paymentExecution = new PaymentExecution();
	    paymentExecution.setPayerId(req.getPayerID());
	    try {
	        APIContext context = new APIContext("Adx8jkEh9Spw-52awuJsOxPbgylg_ABQ1ToE4ig3Hk_ezhprEHrri84m7vSTlRmcVWEcYlBx-Br5jQvb","ECJy1OsgQbB1Sm4MzBo-5A_Fr-bn9jAgrR5EyEM4pak0yIH1quxkV9lHJK9fdykVLLjG346Wsllj0AxC", "sandbox");
	        Payment createdPayment = payment.execute(context, paymentExecution);
	        System.out.println(createdPayment);
	        if(createdPayment!=null){
	            response.put("status", "success");
	           // response.put("payment", createdPayment);
	        }
	    } catch (PayPalRESTException e) {
	        System.err.println(e.getDetails());
	    }
	    
	    
	    return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
