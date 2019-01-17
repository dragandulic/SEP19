package com.example.MSpaypal.controller;

public class PaymentMFDTO {

private Long idPaymentObj;
	
	private Long idCustomer;
	private String nameCustomer;
	private String surnameCustomer;
	private String emailCustomer;
	
	private Long idSeller;
	private String nameSeller;
	private String issnumberSeller;
	
	private double amount;
	
	private String title;
	
	public PaymentMFDTO() {
		
	}


	public Long getIdPaymentObj() {
		return idPaymentObj;
	}


	public void setIdPaymentObj(Long idPaymentObj) {
		this.idPaymentObj = idPaymentObj;
	}


	public Long getIdCustomer() {
		return idCustomer;
	}


	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}


	public String getNameCustomer() {
		return nameCustomer;
	}


	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}


	public String getSurnameCustomer() {
		return surnameCustomer;
	}


	public void setSurnameCustomer(String surnameCustomer) {
		this.surnameCustomer = surnameCustomer;
	}


	public String getEmailCustomer() {
		return emailCustomer;
	}


	public void setEmailCustomer(String emailCustomer) {
		this.emailCustomer = emailCustomer;
	}


	public Long getIdSeller() {
		return idSeller;
	}


	public void setIdSeller(Long idSeller) {
		this.idSeller = idSeller;
	}


	public String getNameSeller() {
		return nameSeller;
	}


	public void setNameSeller(String nameSeller) {
		this.nameSeller = nameSeller;
	}


	public String getIssnumberSeller() {
		return issnumberSeller;
	}


	public void setIssnumberSeller(String issnumberSeller) {
		this.issnumberSeller = issnumberSeller;
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
	
	
}
