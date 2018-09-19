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
	 * 
	 */
	@Test
	public void createNewBookingTest() throws Exception {

		// Populate the roomDetailsDTO
		List<RoomDetailsDTO> rooms = new ArrayList<RoomDetailsDTO>();
		roomDetailsDTO = new RoomDetailsDTO();
		roomDetailsDTO.setId(1);
		roomDetailsDTO.setSize("Single");
		rooms.add(roomDetailsDTO);

		roomDetailsDTO.setId(5);
		roomDetailsDTO.setSize("Double");

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
	 * 
	 */
	@Test
	public void getBookingByRoomIdTest() throws Exception {

		// Populate the roomDetailsDTO
		List<RoomDetailsDTO> rooms = new ArrayList<RoomDetailsDTO>();
		roomDetailsDTO = new RoomDetailsDTO();
		roomDetailsDTO.setId(3);
		roomDetailsDTO.setSize("Double");
		rooms.add(roomDetailsDTO);

		// populate the CustomerDetailsDTO
		customerDetailsDTO = new CustomerDetailsDTO();
		Date checkInDate = Date.valueOf("2018-8-31");
		Date checkOutDate = Date.valueOf("2018-9-15");
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
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").isNumber());

	}

	/*
	 * 
	 */
	@Test
	public void getBookingByCustomerIdTest() throws Exception {
		// Populate the roomDetailsDTO
		List<RoomDetailsDTO> rooms = new ArrayList<RoomDetailsDTO>();
		roomDetailsDTO = new RoomDetailsDTO();
		roomDetailsDTO.setId(2);
		roomDetailsDTO.setSize("Single");
		rooms.add(roomDetailsDTO);

		// Populate customer details DTO
		customerDetailsDTO = new CustomerDetailsDTO();
		Date checkInDate = Date.valueOf("2018-8-31");
		Date checkOutDate = Date.valueOf("2018-9-14");
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
				.andExpect(jsonPath("$.bookingId").isNumber())
				.andReturn();

		JSONObject json = new JSONObject(result.getResponse().getContentAsString());
		//Get the customer Object
		JSONObject jsonCustomer = json.getJSONObject("customerDetailsDTO");
		//Get the customer 
		Integer id = (Integer) jsonCustomer.getInt("customerId");

		mvc.perform(MockMvcRequestBuilders.get("/getBookingByCustomerId/" + id).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty());

	}

	/*
	 * 
	 */
	@Test
	public void updateBookingByBookingIdTest() throws Exception {

		// Populate Room details DTO
		List<RoomDetailsDTO> rooms = new ArrayList<RoomDetailsDTO>();
		roomDetailsDTO = new RoomDetailsDTO();
		roomDetailsDTO.setId(4);
		roomDetailsDTO.setSize("Double");
		rooms.add(roomDetailsDTO);

		// Populate Customer details DTO
		customerDetailsDTO = new CustomerDetailsDTO();
		Date checkInDate = Date.valueOf("2018-9-18");
		Date checkOutDate = Date.valueOf("2018-9-20");
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

	
		customerDetailsDTO.setCustomerId(id + 1);
		bookingDetailsDTO.setCustomerDetailsDTO(customerDetailsDTO);

		mvc.perform(MockMvcRequestBuilders.put("/updateExistingBooking/" + id).content(toJson(bookingDetailsDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		mvc.perform(MockMvcRequestBuilders.get("/getBookingByRoomId/4").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$.bookingId").value(json.get("bookingId")));

	}
	
	/*
	 * 
	 */
	private byte[] toJson(Object r) throws Exception {
		ObjectMapper map = new ObjectMapper();
		return map.writeValueAsString(r).getBytes();
	}
} 