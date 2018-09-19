package com.springboot.repository;

import java.sql.Date;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.springboot.entity.BookingDetails;

/*
 * Repository class for Booking Details
 */
public interface BookingRepository extends CrudRepository<BookingDetails, Integer>{	
	
	@Query("select a from BookingDetails a where a.customer.customerId=?")
	public BookingDetails getBookingByCustomerId(Integer customerId);

	@Query("select a from BookingDetails a where a.roomDetails.roomId=?")
	public BookingDetails getBookingByRoomId(Integer roomId);
	
	@Query("select a from BookingDetails a where a.bookingId=?")
	public BookingDetails getBookingByBookingId(Integer bookingId);
	
	@Query("select a from BookingDetails a where a.roomDetails.roomId=?1 AND a.customer.checkIn=?2 AND a.customer.checkOut=?3")
	public BookingDetails getAvailability(Integer roomId, Date checkIn, Date checkOut);

}
