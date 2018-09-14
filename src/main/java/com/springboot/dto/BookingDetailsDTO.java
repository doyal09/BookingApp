package com.springboot.dto;

import java.sql.Date;
import java.util.List;

import com.springboot.entity.*;


public class BookingDetailsDTO {
	
	private Integer id;
	private Integer bookingId;
	private Integer customerId;
	private List<RoomDetails> roomDetails;
	private String breakfastOption;
	private Integer noOfMembers;
	private double totalCost;
	private Date checkIn;
	private Date checkOut;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public List<RoomDetails> getRoomDetails() {
		return roomDetails;
	}
	public void setRoomDetails(List<RoomDetails> roomDetails) {
		this.roomDetails = roomDetails;
	}
	public String getBreakfastOption() {
		return breakfastOption;
	}
	public void setBreakfastOption(String breakfastOption) {
		this.breakfastOption = breakfastOption;
	}
	public Integer getNoOfMembers() {
		return noOfMembers;
	}
	public void setNoOfMembers(Integer noOfMembers) {
		this.noOfMembers = noOfMembers;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	public Date getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	
}
