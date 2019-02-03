package com.example.MSpaypal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.example.MSpaypal.controller.PayPalDTO;
import com.example.MSpaypal.controller.PayPalResponse;
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
	    redirectUrls.setReturnUrl("http://localhost:3000/paypal/success");
	    
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
	            response.put("client_url",dto.getSuccessUrl());
	            System.out.println(redirectUrl);
	        }
	    } catch (PayPalRESTException e) {
	        System.out.println("Error happened during payment creation!");
	    }
	    
	    
	    return response;
	}
	
	
	
	public PayPalResponse completePayment(PaypalConfirmDTO req){
		
		PayPalResponse response=new PayPalResponse();
	    
	    Payment payment = new Payment();
	    payment.setId(req.getPaymentId());
	    

	    PaymentExecution paymentExecution = new PaymentExecution();
	    paymentExecution.setPayerId(req.getPayerID());
	    try {
	        APIContext context = new APIContext("Abfq6qRnGEz_CGD2-nVh3BuNVYl4OZgMDM8XK2qzeyhlku2dWy5QcgHg_gzQhlulUqqo8Dyhn5JWpEl2","EKoyrJq2z345XtWQ88lgQiR7gpxtdnldqkuZz_Ud3eNX5BwinXzMkP5Mq07Nj2qk9gne0k6sm5blF2Tm", "sandbox");
	        Payment createdPayment = payment.execute(context, paymentExecution);
	        System.out.println(createdPayment);
	      
	        if(createdPayment!=null){
	        	
	            response.setStatus(createdPayment.getState());
	            response.setPayeremail(createdPayment.getPayer().getPayerInfo().getEmail());
	            response.setTime(createdPayment.getCreateTime());
	            response.setPaymentid(createdPayment.getId());
	            response.setMerchant(createdPayment.getTransactions().get(0).getPayee().getEmail());
	            response.setDescription(createdPayment.getTransactions().get(0).getDescription());
	            response.setAmount(createdPayment.getTransactions().get(0).getAmount().getTotal());
	            response.setType("paypal");
	            response.setCurrency("USD");
	            
	           
	          
	           // response.put("payment", createdPayment);
	        }
	    } catch (PayPalRESTException e) {
	        System.err.println(e.getDetails());
	    }
	    
	    
	    return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
