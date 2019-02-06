package com.example.KPzuulproxy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ObjectPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String code;
	
	private double amount;
	
	private String title;
	
	private String description;
	
	private String merchantid;

	private String merchantpassword;
	
	private String clientId;
	
	private String clientSecret;
	
	private String successUrl;

	private boolean verified;
	
	private String type; // bitcoin,paypal,bank account
	
	private String currency;
	
	private String datetime;
	
	private String merchantmail;
	
	private String payermail;
	
	private String paymentid;
	
	private String magazinename;
	
	private String fronturl;
	
	public ObjectPayment() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMerchantid() {
		return merchantid;
	}

	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}

	public String getMerchantpassword() {
		return merchantpassword;
	}

	public void setMerchantpassword(String merchantpassword) {
		this.merchantpassword = merchantpassword;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
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

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getMerchantmail() {
		return merchantmail;
	}

	public void setMerchantmail(String merchantmail) {
		this.merchantmail = merchantmail;
	}

	public String getPayermail() {
		return payermail;
	}

	public void setPayermail(String payermail) {
		this.payermail = payermail;
	}

	public String getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}

	public String getMagazinename() {
		return magazinename;
	}

	public void setMagazinename(String magazinename) {
		this.magazinename = magazinename;
	}

	public String getFronturl() {
		return fronturl;
	}

	public void setFronturl(String fronturl) {
		this.fronturl = fronturl;
	}


	
	
	
}
