package com.example.KPzuulproxy.dto;

public class PayPalResponse {

	
	private String status;
	private String payeremail;
	private String paymentid;
	private String description;
	private String time;
	private String merchant;
	private String currency;
	private String type;
	private String amount;
	
	
	
	
	public PayPalResponse(){
		
	}




	public PayPalResponse(String status,String payeremail, String paymentid, String description,
			String time, String merchant, String currency, String type, String amount) {
		super();
		this.status = status;
	
		this.payeremail = payeremail;
		this.paymentid = paymentid;
		this.description = description;
		this.time = time;
		this.merchant = merchant;
		this.currency = currency;
		this.type = type;
		this.amount = amount;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
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




	public String getCurrency() {
		return currency;
	}




	public void setCurrency(String currency) {
		this.currency = currency;
	}




	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	public String getAmount() {
		return amount;
	}




	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
	
	
	
	
	
	
}
