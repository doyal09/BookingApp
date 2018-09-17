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
import com.springboot.dto.CustomerDetailsDTO;
import com.springboot.dto.RoomDetailsDTO;
import com.springboot.entity.BookingDetails;
import com.springboot.entity.Customer;
import com.springboot.entity.RoomDetails;
import com.springboot.repository.BookingRepository;
import com.springboot.repository.CustomerRepository;

@Service
public class BookingService {
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private CustomerRepository customerRepository;

	Logger logger = LoggerFactory.getLogger(BookingController.class);

	/*
	 * Method in SERVICE class to get the Booking Details by ROOM id returns
	 * object of BookingDetails
	 */
	public BookingDetails getBookingByRoomId(Integer roomId) {
		logger.debug("Entering getBookingByRoomId");
		BookingDetails obj = bookingRepository.getBookingByRoomId(roomId);
		logger.debug("Exiting getBookingByRoomId");
		return obj;
	}

	/*
	 * Method in SERVICE to get the Booking Details by Booking id returns object
	 * of BookingDetails
	 */
	public BookingDetails getBookingByBookingId(Integer bookingId) {
		logger.debug("Entering getBookingByBookingId");
		BookingDetails obj = bookingRepository.getBookingByBookingId(bookingId);
		logger.debug("Exiting getBookingByBookingId");
		return obj;
	}

	/*
	 * Method in SERVICE to get the Booking Details by Customer ID returns
	 * object of BookingDetails
	 */
	public BookingDetails getBookingByCustomerId(Integer customerId) {
		logger.debug("Entering getBookingByCustomerId");
		BookingDetails obj = bookingRepository.getBookingByCustomerId(customerId);
		logger.debug("Exiting getBookingByCustomerId");
		return obj;

	}

	/*
	 * Method in SERVICE to create a NEW BOOKING, CUSTOMER and update ROOM
	 * DETAILS returns object of BookingDetailsDTO
	 */

	public BookingDetailsDTO createNewBooking(BookingDetailsDTO bookingDetailsDTO) {
		logger.debug("Entering createNewBooking");
		// Generate random booking id
		// 9999 is the maximum and the 1000 is our minimum

		double totalBookingCost = 0.0;

		Random rand = new Random();
		int bookingId = rand.nextInt(9999) + 1000;
		int customerId = bookingId + HotelConstants.ONE;
		logger.debug("Booking ID is:  " + bookingId);
		logger.debug("Customer ID is: " + customerId);

		// Entity object for BookingDetailsDTO, RoomDetailsDTO ,
		// CustomerDetailsDTO
		BookingDetails bookingDetailsObject = new BookingDetails();
		RoomDetails roomDetailsObject = new RoomDetails();
		Customer customerDetailsObject = new Customer();

		// Get the list of rooms from the bookingDetailsDTO
		List<RoomDetailsDTO> rooms = bookingDetailsDTO.getRoomDetailsDTO();
		// Get the customer details from the bookingDetailsDTO
		CustomerDetailsDTO customerDTO = bookingDetailsDTO.getCustomerDetailsDTO();
		customerDTO.setCustomerId(customerId);
		for (RoomDetailsDTO roomDetailsDTO : rooms) {
			if (HotelConstants.SINGLE.equalsIgnoreCase(roomDetailsDTO.getSize())
					&& HotelConstants.TRUE.equalsIgnoreCase(roomDetailsDTO.getAvailability())) {
				logger.debug("RoomType is: " + roomDetailsDTO.getSize() + "and Room Availability is: "
						+ roomDetailsDTO.getAvailability());
				totalBookingCost = totalBookingCost + HotelConstants.SINGLE_PRICE;
				logger.debug("Total booking Cost in if part is : " + totalBookingCost);
			} else {
				logger.debug("RoomType is: " + roomDetailsDTO.getSize() + "and Room Availability is: "
						+ roomDetailsDTO.getAvailability());
				totalBookingCost = totalBookingCost + HotelConstants.DOUBLE_PRICE;
				logger.debug("Total booking Cost in else part : " + totalBookingCost);
			}

			bookingDetailsObject.setBookingId(bookingId);
			bookingDetailsObject.setCustomerId(customerId);
			bookingDetailsObject.setRoomId(roomDetailsDTO.getId());
			roomDetailsObject.setAvailability(HotelConstants.FALSE);
			// updateRoomDetails(roomDetails.getId());
			bookingRepository.save(bookingDetailsObject);
		}
		// Update the Customer table with details
		customerDetailsObject.setCustomerId(customerId);
		customerDetailsObject.setCustomerFirstName(customerDTO.getCustFirstName());
		customerDetailsObject.setCustomerLastName(customerDTO.getCustLastName());
		customerDetailsObject.setBreakfastOption(customerDTO.getBreakfastOption());
		customerDetailsObject.setNoOfMembers(customerDTO.getNoOfMembers());
		customerDetailsObject.setCheckIn(customerDTO.getCheckIn());
		customerDetailsObject.setCheckOut(customerDTO.getCheckOut());
		customerRepository.save(customerDetailsObject);

		// get breakfast cost for the number of members
		if (HotelConstants.TRUE.equalsIgnoreCase(customerDTO.getBreakfastOption())) {
			totalBookingCost = totalBookingCost + retrieveCostOfBreakfast(customerDTO);
			logger.debug("Total Booking cost after inclusion of Breakfast is: " + totalBookingCost);
		}
		// return the DTO with bookingId and total booking cost
		bookingDetailsDTO.setBookingId(bookingId);
		bookingDetailsDTO.setTotalCost(totalBookingCost);
		bookingDetailsDTO.setCustomerId(customerId);
		logger.debug("Exiting createNewBooking");
		return bookingDetailsDTO;
	}

	/*
	 * Method in SERVICE class to Update an existing booking
	 */
	public void updateBooking(BookingDetails bookingDetails) {
		logger.debug("Inside updateBooking method");
		bookingRepository.save(bookingDetails);
		logger.debug("Exiting updateBooking");
	}

	/*
	 * Method in SERVICE class to retrieve breakfast inclusion cost
	 */
	public double retrieveCostOfBreakfast(CustomerDetailsDTO customerDetailsDTO) {
		logger.debug("Inside retrieveCostOfBreakfast");
		double breakfastCost = customerDetailsDTO.getNoOfMembers() * HotelConstants.BREAKFAST_COST;
		logger.debug("Breakfast Cost is: " + breakfastCost);
		logger.debug("Exiting retrieveCostOfBreakfast");
		return breakfastCost;
	}

	/*
	 * Update availability of Room based on Room Id
	 */
	public void updateRoomDetails(Integer roomId) {
		// bookingRepository.updateRoomDetails(roomId);

	}

}
