package com.example.MSbank.dto;

public class PaymentObjDTO {
	
	private Long idPaymentObj;
	
	private Long idCustomer;
	private String nameCustomer;
	private String surnameCustomer;
	private String emailCustomer;
	
	private Long idSeller;
	private String nameSeller;
	private String issnumberSeller;
	private String merchant_id;
	private String merchant_password;
	private double amountMag;
	
	
	private double amount;
	
	private String title;
	
	public PaymentObjDTO() {
		
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

	public String getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getMerchant_password() {
		return merchant_password;
	}

	public void setMerchant_password(String merchant_password) {
		this.merchant_password = merchant_password;
	}

	public double getAmountMag() {
		return amountMag;
	}

	public void setAmountMag(double amountMag) {
		this.amountMag = amountMag;
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
