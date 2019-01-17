package com.example.MSpaypal.controller;

public class PaypalConfirmDTO {
	
	
	private String  paymentId;
	private String  payerID;
	
	
	
	PaypalConfirmDTO(){
		
	}



	
	
	
	
	public PaypalConfirmDTO(String paymentId, String payerID) {
		super();
		this.paymentId = paymentId;
		this.payerID = payerID;
	}







	public String getPaymentId() {
		return paymentId;
	}



	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}



	public String getPayerID() {
		return payerID;
	}



	public void setPayerID(String payerID) {
		this.payerID = payerID;
	}
	
	
	
	
	
	
	
	
	
	

}
