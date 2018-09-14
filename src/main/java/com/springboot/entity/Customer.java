package com.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model class for the CUSTOMER table.
 * @author isapjtg
 *
 */
@Entity
@Table
public class Customer {

	@Id
	@Column(name = "customerId", nullable = false)
	private Integer customerId;
	@Column(name = "title", length = 15, nullable=false)
	private String title;
	@Column(name = "customerFirstName", length = 500)
	private String customerFirstName;
	@Column(name = "customerLastName", length = 500)
	private String customerLastName;
	@Column(name = "customerBill")
	private double customerBill;
	
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public double getCustomerBill() {
		return customerBill;
	}

	public void setCustomerBill(double customerBill) {
		this.customerBill = customerBill;
	}
}
