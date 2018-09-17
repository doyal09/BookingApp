package com.springboot.dto;

import java.sql.Date;
import java.util.List;

import com.springboot.entity.*;


public class BookingDetailsDTO {
	
	private Integer bookingId;
	private Integer customerId;
	private List<RoomDetailsDTO> roomDetailsDTO;
	private CustomerDetailsDTO customerDetailsDTO;
	private double totalCost;
	
	/*
	 * Getting the booking id
	 * returns Integer
	 */
	public Integer getBookingId() {
		return bookingId;
	}
	
	/*
	 * Setting the booking id
	 * @param Integer bookingId
	 */
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	
	/*
	 * Getting the customerId
	 * returns Integer customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}
	
	/*
	 * Setting the customerId
	 * @param Integer customerId
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	
	/*
	 * Getting the RoomDetails
	 * returns roomDetailsDTO
	 */
	public List<RoomDetailsDTO> getRoomDetailsDTO() {
		return roomDetailsDTO;
	}
	
	/*
	 * Setting the roomDetailsDTO
	 * @param List of RoomDetailDTO
	 */
	public void setRoomDetailsDTO(List<RoomDetailsDTO> roomDetailsDTO) {
		this.roomDetailsDTO = roomDetailsDTO;
	}
	
	/*
	 * Getting the CustomerDetailsDTO
	 * returns object of CustomerDetailsDTO
	 */
	public CustomerDetailsDTO getCustomerDetailsDTO() {
		return customerDetailsDTO;
	}
	
	/*
	 * Setting the CustomerDetailsDTO
	 * @param customerDetailsDTO
	 */
	public void setCustomerDetailsDTO(CustomerDetailsDTO customerDetailsDTO) {
		this.customerDetailsDTO = customerDetailsDTO;
	}
	
	/*
	 * Getting Total Cost of the booking
	 * returns double
	 */
	
	public double getTotalCost() {
		return totalCost;
	}
	
	/*
	 * Setting the total cost of the booking
	 * @param totalCost
	 */
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
		
}
