package com.springboot.dto;

import java.util.List;

/*
 * DTO class for Booking Details
 */
public class BookingDetailsDTO {
	
	private Integer bookingId;
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
