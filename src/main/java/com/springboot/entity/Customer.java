package com.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

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
	@Column(name = "customerFirstName", length = 500)
	private String customerFirstName;
	@Column(name = "customerLastName", length = 500)
	private String customerLastName;
	@Column(name = "breakfastOption")
	private String breakfastOption;
	@Column(name = "noOfMembers")
	private Integer noOfMembers;
	@Column(name = "checkIn")
	private Date checkIn;
	@Column(name = "checkOut")
	private Date checkOut;

	/*
	 * 
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/*
	 * 
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/*
	 * 
	 */
	public String getCustomerFirstName() {
		return customerFirstName;
	}

	/*
	 * 
	 */
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	/*
	 * 
	 */
	public String getCustomerLastName() {
		return customerLastName;
	}

	/*
	 * 
	 */
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	/*
	 * 
	 */
	public String getBreakfastOption() {
		return breakfastOption;
	}

	/*
	 * 
	 */
	public void setBreakfastOption(String breakfastOption) {
		this.breakfastOption = breakfastOption;
	}

	/*
	* 
	*/
	public Integer getNoOfMembers() {
		return noOfMembers;
	}

	/*
	 * 
	 */
	public void setNoOfMembers(Integer noOfMembers) {
		this.noOfMembers = noOfMembers;
	}

	/*
	 * 
	 */
	public Date getCheckIn() {
		return checkIn;
	}

	/*
	 * 
	 */
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	/*
	 * 
	 */
	public Date getCheckOut() {
		return checkOut;
	}

	/*
	 * 
	 */
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

}
