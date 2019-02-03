package com.example.MSpaypal.controller;

public class PayPalResponse {
	
	
	private String status;
	private String payment_method;
	private String payeremail;
	private String paymentid;
	private String description;
	private String payername;
	private String payersurname;
	private String time;
	private String merchant;
	
	
	
	public PayPalResponse(){
		
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPayment_method() {
		return payment_method;
	}


	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}


	public String getPayeremail() {
		return payeremail;
	}


	public void setPayeremail(String payeremail) {
		this.payeremail = payeremail;
	}


	public String getPaymentid() {
		return paymentid;
	}


	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getPayername() {
		return payername;
	}


	public void setPayername(String payername) {
		this.payername = payername;
	}


	public String getPayersurname() {
		return payersurname;
	}


	public void setPayersurname(String payersurname) {
		this.payersurname = payersurname;
	}



	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getMerchant() {
		return merchant;
	}


	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	
	
	
	
	
	

}
