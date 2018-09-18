package com.springboot.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.constants.HotelConstants;
import com.springboot.dto.BookingDetailsDTO;
import com.springboot.dto.CustomerDetailsDTO;
import com.springboot.entity.BookingDetails;
import com.springboot.exception.BookingAppCustomException;
import com.springboot.service.BookingService;

@RestController
public class BookingController {

	Logger logger = LoggerFactory.getLogger(BookingController.class);
	@Autowired
	private BookingService bookingService;

	/*
	 * Method that handles the query against a Room id.
	 * 
	 * @param request, the HttpServletRequest object
	 * 
	 * @param response, the HttpServletResponse object
	 */
	@RequestMapping(value = "/getBookingByRoomId/{roomId}", method = RequestMethod.GET)
	public ResponseEntity<?> getBookingByRoomId(@PathVariable("roomId") Integer roomId) {
		logger.debug("INSIDE getBookingByRoomId");
		BookingDetails bookingDetails = null;
		try {
			logger.debug("Room ID is: " + roomId);
			if (HotelConstants.ZERO < roomId) {
				bookingDetails = bookingService.getBookingByRoomId(roomId);
				if (null != bookingDetails) {
					logger.debug("Booking details is NOT NULL");
					return new ResponseEntity<BookingDetails>(bookingDetails, HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("Booking details not available against the room id",
							HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<String>("Room ID is not of the correct format", HttpStatus.BAD_REQUEST);
			}
		} catch (BookingAppCustomException e) {
			return new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * Method that handles the query against a Customer id.
	 * 
	 * @param request, the HttpServletRequest object
	 * 
	 * @param response, the HttpServletResponse object
	 */
	@RequestMapping(value = "/getBookingByCustomerId/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<?> getBookingByCustomerId(@PathVariable("customerId") Integer customerId) {
		logger.debug("INSIDE getBookingByCustomerId");
		try {
			logger.debug("Customer id is: " + customerId);
			BookingDetails bookingDetails = bookingService.getBookingByCustomerId(customerId);
			if (null != bookingDetails) {
				logger.debug("Customer id is: " + customerId);
			}
			return new ResponseEntity<BookingDetails>(bookingDetails, HttpStatus.OK);
		} catch (BookingAppCustomException e) {
			return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<String>("Booking details not available against the customer id",
					HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * Method that Creates a New booking.
	 * 
	 * @param request, the HttpServletRequest object
	 * 
	 * @param response, the HttpServletResponse object
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> createNewBooking(@RequestBody @Valid BookingDetailsDTO bookingDetailsDTO) {
		logger.debug("INSIDE createNewBooking");
		try {
			bookingDetailsDTO = bookingService.createNewBooking(bookingDetailsDTO);
			return new ResponseEntity<BookingDetailsDTO>(bookingDetailsDTO, HttpStatus.CREATED);
		} catch (BookingAppCustomException e) {
			return new ResponseEntity<String>("Internal Server error has occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}

	/*
	 * Method that updates an existing booking.
	 * 
	 * @param request, the HttpServletRequest object
	 * 
	 * @param response, the HttpServletResponse object
	 */
	@RequestMapping(value = "/updateExistingBooking/{bookingId}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<?> updateExistingBooking(@PathVariable("bookingId") Integer bookingId,
			@RequestBody @Valid BookingDetailsDTO bookingDetailsDTO) {
		logger.debug("INSIDE updateExistingBooking");
		BookingDetails currentBookingDetails = null;
		try {
			currentBookingDetails = bookingService.getBookingByBookingId(bookingId);
			if (null == currentBookingDetails) {
				logger.debug("Current Booking Details is NULL");
				return new ResponseEntity<String>("Unable to update. No booking found with the booking id ",
						HttpStatus.NOT_FOUND);
			} else {
				CustomerDetailsDTO customerDetailsDTO = bookingDetailsDTO.getCustomerDetailsDTO();
				// set new customer id
				currentBookingDetails.setCustomerId(customerDetailsDTO.getCustomerId());
				bookingService.updateBooking(currentBookingDetails);
				return new ResponseEntity<BookingDetails>(currentBookingDetails, HttpStatus.OK);
			}
		} catch (BookingAppCustomException ex) {
			return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
