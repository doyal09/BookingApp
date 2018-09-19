package com.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	@ManyToOne
	@JoinColumn(name = "roomId")
	private RoomDetails roomDetails;
	@OneToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	
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
	 * Getting the RoomDetails
	 * return RoomDetails roomDetails
	 */
	public RoomDetails getRoomDetails() {
		return roomDetails;
	}

	/*
	 * Setting the RoomDetails
	 * @param roomDetails
	 */
	public void setRoomDetails(RoomDetails roomDetails) {
		this.roomDetails = roomDetails;
	}
	
	/*
	 * Getting the Customer
	 * return Object of Customer customer 
	 */
	public Customer getCustomer() {
		return customer;
	}

	/*
	 * Setting Customer
	 * @param Customer customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

}
