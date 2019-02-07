package com.example.MSbitcoin.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class PaymentObjDTO {

	
	private Long id;
	
	private double amount;
	
	private String title;
	
	private String description;
	
	private String code;
	
	private String bitcointoken;
	
	public PaymentObjDTO() {
		
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBitcointoken() {
		return bitcointoken;
	}

	public void setBitcointoken(String bitcointoken) {
		this.bitcointoken = bitcointoken;
	}


	
	
	
}
