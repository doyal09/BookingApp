package com.springboot.service;

import com.springboot.dto.BookingDetailsDTO;
import com.springboot.entity.BookingDetails;

public interface IBookingService {

	// For adding a new booking
	BookingDetailsDTO createNewBooking(BookingDetailsDTO bookingDetailsDTO);

	// For getting booking details against a room id
	BookingDetails getBookingByRoomId(Integer roomId);

	// For getting booking details against a customer id
	BookingDetails getBookingByCustomerId(Integer customerId);

	// For updating existing booking
	void updateBooking(BookingDetails bookingDetails);
	
	// For getting the booking details from booking id
	BookingDetails getBookingByBookingId(Integer id);

}
