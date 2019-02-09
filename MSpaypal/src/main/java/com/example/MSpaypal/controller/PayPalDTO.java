package com.example.MSpaypal.controller;

public class PayPalDTO {

	
	
	private long amount;
	private String clientId;
	private String clientSecret;
	private String successUrl;
	private String description;
	
	
	

	
	
	public PayPalDTO(){
		
	}






	public long getAmount() {
		return amount;
	}






	public void setAmount(long amount) {
		this.amount = amount;
	}






	public String getClientId() {
		return clientId;
	}






	public void setClientId(String clientId) {
		this.clientId = clientId;
	}






	public String getClientSecret() {
		return clientSecret;
	}






	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}






	public String getSuccessUrl() {
		return successUrl;
	}






	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}






	public String getDescription() {
		return description;
	}






	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
}
