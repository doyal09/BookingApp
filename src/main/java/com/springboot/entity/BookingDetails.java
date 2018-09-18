package com.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * Model class for the BookingDetails table.
 * @author isapjtg
 *
 */
@Entity
@Table (name = "BookingDetails")
public class BookingDetails {
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable=false)
	private Integer id;
	@Column(name = "bookingId", nullable=false)
	private Integer bookingId;
	@Column(name = "customerId")
	private Integer customerId;
	@JoinColumn(name = "roomId")
	private Integer roomId;
	
	/*
	 * Constructor of BookingDetails Entity class
	 */
	public BookingDetails() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Getting the Id
	 * returns integer
	 */
	public Integer getId() {
		return id;
	}
	
	/*
	 * Setting the id
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/*
	 * Getting the BookingId
	 * returns Integer bookingId
	 */
	public Integer getBookingId() {
		return bookingId;
	}
	
	/*
	 * setting the booking id
	 * @param Integer bookingId
	 */
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	
	/*
	 * Getting the Customer id
	 * retuens customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}
	
	/*
	 * Setting the CustomerId
	 * @param Integer customerId
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	/*
	 * Getting the RoomId
	 * returns the roomId
	 */
	public Integer getRoomId() {
		return roomId;
	}
	
	/*
	 * Setting the Room Id
	 * @param roomId
	 */
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	

}
