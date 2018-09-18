package com.springboot.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.MyApplication;
import com.springboot.dto.BookingDetailsDTO;
import com.springboot.dto.CustomerDetailsDTO;
import com.springboot.dto.RoomDetailsDTO;

/*
 * TEST class for Booking App
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=MyApplication.class)
public class BookingDetailsTest {

	@Autowired
	private WebApplicationContext context;
	
	private BookingDetailsDTO bookingDetailsDTO;
	private CustomerDetailsDTO customerDetailsDTO;
	private RoomDetailsDTO roomDetailsDTO;
	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	/*
	 * Create New Booking Test method
	 */
	@Test
	public void createNewBookingTest() throws Exception {

		// Populate the roomDetailsDTO
		List<RoomDetailsDTO> rooms = new ArrayList<RoomDetailsDTO>();
		roomDetailsDTO = new RoomDetailsDTO();
		roomDetailsDTO.setId(1);
		roomDetailsDTO.setSize("Single");
		roomDetailsDTO.setAvailability("true");
		rooms.add(roomDetailsDTO);

		roomDetailsDTO.setId(5);
		roomDetailsDTO.setSize("Double");
		roomDetailsDTO.setAvailability("true");

		rooms.add(roomDetailsDTO);

		// populate the CustomerDetailsDTO
		customerDetailsDTO = new CustomerDetailsDTO();
		Date checkInDate = Date.valueOf("2018-8-31");
		Date checkOutDate = Date.valueOf("2018-9-9");
		customerDetailsDTO.setCustFirstName("Johnny");
		customerDetailsDTO.setCustLastName("Bravo");
		customerDetailsDTO.setCheckIn(checkInDate);
		customerDetailsDTO.setCheckOut(checkOutDate);
		customerDetailsDTO.setBreakfastOption("true");
		customerDetailsDTO.setNoOfMembers(4);

		// Populate Booking Details DTO
		bookingDetailsDTO = new BookingDetailsDTO();
		bookingDetailsDTO.setRoomDetailsDTO(rooms);
		bookingDetailsDTO.setCustomerDetailsDTO(customerDetailsDTO);

		mvc.perform(MockMvcRequestBuilders.post("/create").content(toJson(bookingDetailsDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.bookingId").isNumber())
				.andExpect(jsonPath("$.totalCost").isNumber());

	}

	/*
	 * GET booking by Room ID test method 
	 */
	@Test
	public void getBookingByRoomIdTest() throws Exception {

		// Populate the roomDetailsDTO
		List<RoomDetailsDTO> rooms = new ArrayList<RoomDetailsDTO>();
		roomDetailsDTO = new RoomDetailsDTO();
		roomDetailsDTO.setId(3);
		roomDetailsDTO.setSize("Double");
		roomDetailsDTO.setAvailability("true");
		rooms.add(roomDetailsDTO);

		// populate the CustomerDetailsDTO
		customerDetailsDTO = new CustomerDetailsDTO();
		Date checkInDate = Date.valueOf("2018-8-31");
		Date checkOutDate = Date.valueOf("2018-9-9");
		customerDetailsDTO.setCustFirstName("Sourav");
		customerDetailsDTO.setCustLastName("Ganguly");
		customerDetailsDTO.setCheckIn(checkInDate);
		customerDetailsDTO.setCheckOut(checkOutDate);
		customerDetailsDTO.setBreakfastOption("true");
		customerDetailsDTO.setNoOfMembers(3);

		// Populate Booking Details DTO
		bookingDetailsDTO = new BookingDetailsDTO();
		bookingDetailsDTO.setRoomDetailsDTO(rooms);
		bookingDetailsDTO.setCustomerDetailsDTO(customerDetailsDTO);

		mvc.perform(MockMvcRequestBuilders.post("/create").content(toJson(bookingDetailsDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		mvc.perform(MockMvcRequestBuilders.get("/getBookingByRoomId/2").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").isNumber())
				.andExpect(jsonPath("$.customerId").isNumber()).andExpect(jsonPath("$.roomId").isNumber());

	}

	/*
	 * GET booking by Customer ID test method 
	 */
	@Test
	public void getBookingByCustomerIdTest() throws Exception {
		// Populate the roomDetailsDTO
		List<RoomDetailsDTO> rooms = new ArrayList<RoomDetailsDTO>();
		roomDetailsDTO = new RoomDetailsDTO();
		roomDetailsDTO.setId(2);
		roomDetailsDTO.setSize("Single");
		roomDetailsDTO.setAvailability("true");
		rooms.add(roomDetailsDTO);

		// Populate customer details DTO
		customerDetailsDTO = new CustomerDetailsDTO();
		Date checkInDate = Date.valueOf("2018-8-31");
		Date checkOutDate = Date.valueOf("2018-9-9");
		customerDetailsDTO.setCustFirstName("Daniel");
		customerDetailsDTO.setCustLastName("Radcliffe");
		customerDetailsDTO.setCheckIn(checkInDate);
		customerDetailsDTO.setCheckOut(checkOutDate);
		customerDetailsDTO.setBreakfastOption("false");
		customerDetailsDTO.setNoOfMembers(3);

		// Populate booking details DTO
		bookingDetailsDTO = new BookingDetailsDTO();
		bookingDetailsDTO.setRoomDetailsDTO(rooms);
		bookingDetailsDTO.setCustomerDetailsDTO(customerDetailsDTO);

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post("/create").content(toJson(bookingDetailsDTO))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.totalCost").isNumber())
				.andExpect(jsonPath("$.bookingId").isNumber()).andExpect(jsonPath("$.customerId").isNumber())
				.andReturn();

		JSONObject json = new JSONObject(result.getResponse().getContentAsString());
		Integer id = (Integer) json.getInt("customerId");

		mvc.perform(MockMvcRequestBuilders.get("/getBookingByCustomerId/" + id).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$.customerId").isNumber()).andExpect(jsonPath("$.roomId").isNumber());

	}

	/*
	 * Update Booking by BookingID test method
	 */
	@Test
	public void updateBookingByBookingIdTest() throws Exception {

		// Populate Room details DTO
		List<RoomDetailsDTO> rooms = new ArrayList<RoomDetailsDTO>();
		roomDetailsDTO = new RoomDetailsDTO();
		roomDetailsDTO.setId(4);
		roomDetailsDTO.setSize("Double");
		roomDetailsDTO.setAvailability("true");
		rooms.add(roomDetailsDTO);

		// Populate Customer details DTO
		customerDetailsDTO = new CustomerDetailsDTO();
		Date checkInDate = Date.valueOf("2018-8-31");
		Date checkOutDate = Date.valueOf("2018-9-9");
		customerDetailsDTO.setCustFirstName("Virat");
		customerDetailsDTO.setCustLastName("Kohli");
		customerDetailsDTO.setCheckIn(checkInDate);
		customerDetailsDTO.setCheckOut(checkOutDate);
		customerDetailsDTO.setBreakfastOption("false");
		customerDetailsDTO.setNoOfMembers(2);

		// Populate booking details DTO
		bookingDetailsDTO = new BookingDetailsDTO();
		bookingDetailsDTO.setRoomDetailsDTO(rooms);
		bookingDetailsDTO.setCustomerDetailsDTO(customerDetailsDTO);

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post("/create").content(toJson(bookingDetailsDTO))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.totalCost").isNumber())
				.andExpect(jsonPath("$.bookingId").isNumber()).andReturn();

		JSONObject json = new JSONObject(result.getResponse().getContentAsString());
		Integer id = (Integer) json.getInt("bookingId");

		// customerDetailsDTO.setCustFirstName("Anushka");
		// customerDetailsDTO.setCustLastName("Sharma");
		customerDetailsDTO.setCustomerId(id + 1);
		bookingDetailsDTO.setCustomerDetailsDTO(customerDetailsDTO);

		mvc.perform(MockMvcRequestBuilders.put("/updateExistingBooking/" + id).content(toJson(bookingDetailsDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.get("/getBookingByRoomId/4").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$.bookingId").value(json.get("bookingId")))
				.andExpect(jsonPath("$.roomId").value(4));

	}

	// update booking by booking id
	/*
	 * @Test public void getBookingWithinADateRangeTest() throws Exception {
	 * 
	 * Date checkInDate = Date.valueOf("2018-8-31"); Date checkOutDate =
	 * Date.valueOf("2018-9-9");
	 * 
	 * bookingDetails = new BookingDetails();
	 * bookingDetails.setCustomerId(1114);
	 * bookingDetails.setCheckIn(checkInDate);
	 * bookingDetails.setCheckOut(checkOutDate);
	 * 
	 * mvc.perform(MockMvcRequestBuilders.post("/create").content(toJson(
	 * bookingDetails))
	 * .contentType(MediaType.APPLICATION_JSON).accept(MediaType.
	 * APPLICATION_JSON)) .andExpect(status().isCreated());
	 * 
	 * mvc.perform( MockMvcRequestBuilders.get(
	 * "/getBookingWithinADateRange/checkIn=2018-8-31&checkOut=2018-9-9")
	 * .content(toJson(bookingDetails)).contentType(MediaType.APPLICATION_JSON)
	 * .accept(MediaType.APPLICATION_JSON)) .andExpect(status().isOk());
	 * 
	 * }
	 */

	private byte[] toJson(Object r) throws Exception {
		ObjectMapper map = new ObjectMapper();
		return map.writeValueAsString(r).getBytes();
	}
} 