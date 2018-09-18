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
	 * Gets the customer ID
	 * return Integer customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/*
	 *  Sets the customerId
	 * @param Integer customerId
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/*
	 * Gets the customerFirstName
	 * return String customerFirstName
	 */
	public String getCustomerFirstName() {
		return customerFirstName;
	}

	/*
	 *  Sets the customerFirstName
	 * @param String customerFirstName
	 */
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	/*
	 * Gets the customerLastName
	 * return String customerLastName
	 */
	public String getCustomerLastName() {
		return customerLastName;
	}

	/*
	 *  Sets the customerLastName
	 * @param String customerLastName
	 */
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	/*
	 * Gets the BreakfastOption
	 * return String breakfastOption
	 */
	public String getBreakfastOption() {
		return breakfastOption;
	}

	/*
	 *  Sets the breakfastOption
	 * @param String breakfastOption
	 */
	public void setBreakfastOption(String breakfastOption) {
		this.breakfastOption = breakfastOption;
	}

	/*
	* Gets the noOfMembers
	 * return Integer noOfMembers
	*/
	public Integer getNoOfMembers() {
		return noOfMembers;
	}

	/*
	 *  Sets the noOfMembers
	 * @param Integer noOfMembers
	 */
	public void setNoOfMembers(Integer noOfMembers) {
		this.noOfMembers = noOfMembers;
	}

	/*
	 * Gets the checkIn Date
	 * return Date checkIn
	 */
	public Date getCheckIn() {
		return checkIn;
	}

	/*
	 *  Sets the checkIn date
	 * @param Date checkIn
	 */
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	/*
	 * Gets the checkOut date
	 * return Date checkOut
	 */
	public Date getCheckOut() {
		return checkOut;
	}

	/*
	 * Sets the check Out date
	 * @param Date checkOut
	 */
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

}
