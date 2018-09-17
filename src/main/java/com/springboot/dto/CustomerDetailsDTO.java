package com.springboot.dto;

import java.sql.Date;

/*
 * DTO class for getting the Customer Details
 */

public class CustomerDetailsDTO {
	
	
	private Integer customerId;
	private String custFirstName;
	private String custLastName;
	private String breakfastOption;
	private Integer noOfMembers;
	private Date checkIn;
	private Date checkOut;
	
	/*
	 * Getting the customer id
	 * returns Integer
	 */
	public Integer getCustomerId() {
		return customerId;
	}
	
	/*
	 * Setting the customer id
	 * @param customerId
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	/*
	 * Getting the breakfast option
	 * returns string 
	 */
	public String getBreakfastOption() {
		return breakfastOption;
	}
	
	/*
	 * Sets the Breakfast option
	 * @param breakfastOption
	 */
	public void setBreakfastOption(String breakfastOption) {
		this.breakfastOption = breakfastOption;
	}
	
	/*
	 *Getting the number of members
	 *returns Integer
	 */
	public Integer getNoOfMembers() {
		return noOfMembers;
	}
	
	/*
	 * Setting the number of members 
	 * @param noOfMembers
	 */
	public void setNoOfMembers(Integer noOfMembers) {
		this.noOfMembers = noOfMembers;
	}
	
	/*
	 * Getting the CheckIn date
	 * returns date
	 */
	public Date getCheckIn() {
		return checkIn;
	}
	
	/*
	 *Setting the CheckIn date
	 *@param checkIn 
	 */
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	
	/*
	 * Getting the CheckOut date
	 * return date
	 */
	public Date getCheckOut() {
		return checkOut;
	}
	
	/*
	 * Setting the CheckOut date
	 * @param checkOut
	 */
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	
	/*
	 * Getting the Customer First Name
	 * returns String
	 */
	public String getCustFirstName() {
		return custFirstName;
	}
	
	/*
	 * Setting the CustomerFirstName
	 * @param custFirstName
	 */
	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}
	
	/*
	 * Getting the CustomerLastName
	 * returns String
	 */
	public String getCustLastName() {
		return custLastName;
	}
	
	/*
	 * Setting the CustomerlastName
	 * @param custLastName
	 */
	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}
	
}
