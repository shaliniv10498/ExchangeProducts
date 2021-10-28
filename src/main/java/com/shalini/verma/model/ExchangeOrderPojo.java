package com.shalini.verma.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="exchange_order")
public class ExchangeOrderPojo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_exchange_id")
	private Integer pkExchangeId;
	@Column(name="order_id")
	private Integer orderId;
	
	@ManyToOne
	@JoinColumn(name="fk_product_id")
	private Products productPojoObj;
	@Column(name="asked_price")
	private Integer askedPrice;
	@Column(name="retail_price")
	private Integer retailPrice;
	public Integer getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(Integer retailPrice) {
		this.retailPrice = retailPrice;
	}
	@ManyToOne
	@JoinColumn(name="fk_user_id")
	private CustomerUser user;
	@Column(name="payment_status")
	private String paymentStatus;
	@Column(name="created_on")
	private java.sql.Date createdOn;
	
	
	public Integer getPkExchangeId() {
		return pkExchangeId;
	}
	public void setPkExchangeId(Integer pkExchangeId) {
		this.pkExchangeId = pkExchangeId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Products getProductPojoObj() {
		return productPojoObj;
	}
	public void setProductPojoObj(Products productPojoObj) {
		this.productPojoObj = productPojoObj;
	}
	public Integer getAskedPrice() {
		return askedPrice;
	}
	public void setAskedPrice(Integer askedPrice) {
		this.askedPrice = askedPrice;
	}
	public CustomerUser getUser() {
		return user;
	}
	public void setUser(CustomerUser user) {
		this.user = user;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public java.sql.Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(java.sql.Date createdOn) {
		this.createdOn = createdOn;
	}
	
	

}
