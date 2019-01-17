package com.example.MSpaypal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

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
	
	
	String clientId = "AYXs64yPx_es9JkYpmKDqXfFvcGkQ9PQc8GJhbXX-Q-TDxEFDy8TQfUSJrhHlixFxSW3X1rBq6y2tGL6"; //markivicius@hotmail.com
	String clientSecret = "EGFJGDJ6dvWZqR4I8844WufT2XZi_3pZq7o-93b9PeBV5Ms-GDyDbBZnm-rF1WsMUZEIxFIZGoHKimlr";  //!paypalsep
	
	
	
	public Map<String, Object> createPayment(PaymentMFDTO dto){
	    Map<String, Object> response = new HashMap<String, Object>();
	    Amount amount = new Amount();
	    amount.setCurrency("USD");
	    String price=String.valueOf(dto.getAmount());
	    System.out.println(price   + " Ovo je cenaaa");
	    amount.setTotal(price);
	    Transaction transaction = new Transaction();
	    transaction.setAmount(amount);
	    List<Transaction> transactions = new ArrayList<Transaction>();
	    transactions.add(transaction);

	    Payer payer = new Payer();
	    payer.setPaymentMethod("paypal");

	    Payment payment = new Payment();
	    payment.setIntent("sale");
	    payment.setPayer(payer);
	    payment.setTransactions(transactions);

	    RedirectUrls redirectUrls = new RedirectUrls();
	    redirectUrls.setCancelUrl("http://localhost:3006");
	    redirectUrls.setReturnUrl("http://localhost:3006/#/success");
	    
	    payment.setRedirectUrls(redirectUrls);
	    Payment createdPayment;
	    try {
	        String redirectUrl = "";
	        APIContext context = new APIContext(clientId, clientSecret, "sandbox");
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
	    Map<String, Object> response = new HashMap();
	    Payment payment = new Payment();
	    payment.setId(req.getPaymentId());
	    

	    PaymentExecution paymentExecution = new PaymentExecution();
	    paymentExecution.setPayerId(req.getPayerID());
	    try {
	        APIContext context = new APIContext(clientId, clientSecret, "sandbox");
	        Payment createdPayment = payment.execute(context, paymentExecution);
	        if(createdPayment!=null){
	            response.put("status", "success");
	            response.put("payment", createdPayment);
	        }
	    } catch (PayPalRESTException e) {
	        System.err.println(e.getDetails());
	    }
	    
	    
	    return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
