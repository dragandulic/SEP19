package com.example.KPzuulproxy.dto;

public class PaypalConfirmDTO {

	
	
	private String  paymentId;
	private String  payerID;
	private String  clientId;
	private String  clientSecret;
	
	
	PaypalConfirmDTO(){
		
	}

	public PaypalConfirmDTO(String paymentId, String payerID, String clientId, String clientSecret) {
		super();
		this.paymentId = paymentId;
		this.payerID = payerID;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
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
	
	

	
}
