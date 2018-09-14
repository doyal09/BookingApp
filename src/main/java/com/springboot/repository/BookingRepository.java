package com.springboot.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.springboot.entity.BookingDetails;

public interface BookingRepository extends CrudRepository<BookingDetails, Integer>{	
	
	@Query("select a from BookingDetails a where a.customerId=?")
	public BookingDetails getBookingByCustomerId(Integer customerId);

	@Query("select a from BookingDetails a where a.roomId=?")
	public BookingDetails getBookingByRoomId(Integer roomId);
	
	@Query("select a from BookingDetails a where a.bookingId=?")
	public BookingDetails getBookingByBookingId(Integer bookingId);

}
