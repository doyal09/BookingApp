package com.springboot.service;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.constants.HotelConstants;
import com.springboot.controller.BookingController;
import com.springboot.dto.BookingDetailsDTO;
import com.springboot.entity.BookingDetails;
import com.springboot.entity.RoomDetails;
import com.springboot.repository.BookingRepository;

@Service
public class BookingService implements IBookingService {
	@Autowired
	private BookingRepository bookingRepository;
	Logger logger = LoggerFactory.getLogger(BookingController.class);
	
	@Override
	public BookingDetails getBookingByRoomId(Integer roomId) {
		logger.debug("Entering getBookingByRoomId");
		BookingDetails obj = bookingRepository.getBookingByRoomId(roomId);
		logger.debug("Exiting getBookingByRoomId");
		return obj;
	}
	
	@Override
	public BookingDetails getBookingByBookingId(Integer bookingId) {
		logger.debug("Entering getBookingByBookingId");
		BookingDetails obj = bookingRepository.getBookingByBookingId(bookingId);
		logger.debug("Exiting getBookingByBookingId");
		return obj;
	}
	
	@Override
	public BookingDetails getBookingByCustomerId(Integer customerId) {
		logger.debug("Entering getBookingByCustomerId");
		BookingDetails obj = bookingRepository.getBookingByCustomerId(customerId);
		logger.debug("Exiting getBookingByCustomerId");
		return obj;
		
	}
	
	
	@Override
	public BookingDetailsDTO createNewBooking(BookingDetailsDTO bookingDetailsDTO) {
		logger.debug("Entering createNewBooking");
		// Generate random booking id
		// 9999 is the maximum and the 1000 is our minimum
		Random rand = new Random();
		int bookingId = rand.nextInt(9999) + 1000;
		logger.debug("Booking ID is: "+bookingId);

		BookingDetails bookingDetailsObject = new BookingDetails();
		double totalBookingCost = 0.0;
		
		// Get the list of rooms
		List<RoomDetails> rooms = bookingDetailsDTO.getRoomDetails();
		for (RoomDetails roomDetails : rooms) {
			if (HotelConstants.SINGLE.equalsIgnoreCase(roomDetails.getSize())
					&& HotelConstants.TRUE.equalsIgnoreCase(roomDetails.getAvailability())) {
				logger.debug("RoomType is: "+roomDetails.getSize()+ "and Room Availability is: "+roomDetails.getAvailability());
				totalBookingCost = totalBookingCost + HotelConstants.SINGLE_PRICE;
				logger.debug("Total booking Cost in if part is : "+totalBookingCost);
			} else {
				logger.debug("RoomType is: "+roomDetails.getSize()+ "and Room Availability is: "+roomDetails.getAvailability());
				totalBookingCost = totalBookingCost + HotelConstants.DOUBLE_PRICE;
				logger.debug("Total booking Cost in else part : "+totalBookingCost);
			}

			bookingDetailsObject.setBookingId(bookingId);
			bookingDetailsObject.setCustomerId(bookingDetailsDTO.getCustomerId());
			bookingDetailsObject.setRoomId(roomDetails.getId());
			bookingDetailsObject.setCheckIn(bookingDetailsDTO.getCheckIn());
			bookingDetailsObject.setCheckOut(bookingDetailsDTO.getCheckOut());
			//updateRoomDetails(roomDetails.getId());
			bookingRepository.save(bookingDetailsObject);
		}
		//get breakfast cost for the number of members
		double breakfastInclusionCost = retrieveCostOfBreakfast(bookingDetailsDTO);
		logger.debug("Breakfast inclusion Cost is : "+breakfastInclusionCost);
		totalBookingCost = totalBookingCost + breakfastInclusionCost;
		logger.debug("Total Booking cost after inclusion of Breakfast is: "+totalBookingCost);
		
		//return the DTO with bookingId and total booking cost
		bookingDetailsDTO.setBookingId(bookingId);
		bookingDetailsDTO.setTotalCost(totalBookingCost);
		logger.debug("Exiting createNewBooking");
		return bookingDetailsDTO;
	}
	
	
	@Override
	public void updateBooking(BookingDetails bookingDetails) {
		logger.debug("Inside updateBooking method");
		bookingRepository.save(bookingDetails);
		logger.debug("Exiting updateBooking");
	}
	
	/*
	 * retrieve breakfast inclusion cost
	 */
	public double retrieveCostOfBreakfast(BookingDetailsDTO bookingDetailsDTO) {
		logger.debug("Inside retrieveCostOfBreakfast");
		double breakfastCost = 0.0;
		if(HotelConstants.TRUE.equalsIgnoreCase(bookingDetailsDTO.getBreakfastOption())){
			breakfastCost = bookingDetailsDTO.getNoOfMembers() * HotelConstants.BREAKFAST_COST;
		}
		logger.debug("Exiting retrieveCostOfBreakfast");
		return breakfastCost;
	}

	/*
	 * Update availability of Room based on Room Id
	 */
	public void updateRoomDetails(Integer roomId) {
			//bookingRepository.updateRoomDetails(roomId);

		}

}
