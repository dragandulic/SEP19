package com.example.KPzuulproxy.controller;

public class TransactionDTO {
	
	
	private String status;
	private String payment_method;
	private String payeremail;
	private String paymentid;
	private String description;
	private String time;
	private String merchant;
	private String type;
	private String currency;
	private String amount;
	
	
	public TransactionDTO(){
		
	}

	public TransactionDTO(String status, String payment_method, String payeremail, String paymentid, String description,
			String time, String merchant, String type, String currency, String amount) {
		
		this.status = status;
		this.payment_method = payment_method;
		this.payeremail = payeremail;
		this.paymentid = paymentid;
		this.description = description;
		this.time = time;
		this.merchant = merchant;
		this.type = type;
		this.currency = currency;
		this.amount = amount;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
