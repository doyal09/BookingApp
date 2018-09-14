package com.springboot.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * Model class for the BOOKING_DETAILS table.
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
		@Column(name = "checkIn")
		private Date checkIn;
		@Column(name = "checkOut")
		private Date checkOut;
		
		public BookingDetails() {
			// TODO Auto-generated constructor stub
		}
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
		public Integer getRoomId() {
			return roomId;
		}
		public void setRoomId(Integer roomId) {
			this.roomId = roomId;
		}
		

}
