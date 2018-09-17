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
import com.springboot.entity.RoomDetails;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MyApplication.class)
public class BookingDetailsTest {

	@Autowired
	private WebApplicationContext context;
	private BookingDetailsDTO bookingDetailsDTO;
	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	// addBooking
	@Test
	public void createNewBookingTest() throws Exception {
		
		List<RoomDetails> rooms = new ArrayList<RoomDetails>();
		RoomDetails roomDetails = new RoomDetails();
		roomDetails.setId(1);
		roomDetails.setSize("Single");
		roomDetails.setAvailability("true");
		rooms.add(roomDetails);
				
		roomDetails.setId(5);
		roomDetails.setSize("Double");
		roomDetails.setAvailability("true");
				
		rooms.add(roomDetails);
		
		Date checkInDate = Date.valueOf("2018-8-31");
		Date checkOutDate = Date.valueOf("2018-9-9");
		
		bookingDetailsDTO = new BookingDetailsDTO();
		bookingDetailsDTO.setCustomerId(1111);
		bookingDetailsDTO.setCheckIn(checkInDate);
		bookingDetailsDTO.setCheckOut(checkOutDate);
		bookingDetailsDTO.setBreakfastOption("true");
		bookingDetailsDTO.setRoomDetails(rooms);
		bookingDetailsDTO.setNoOfMembers(5);
		
		mvc.perform(MockMvcRequestBuilders.post("/create").content(toJson(bookingDetailsDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.bookingId").isNumber())
				.andExpect(jsonPath("$.totalCost").isNumber());

	}

	// retrieve booking by room id
	@Test
	public void getBookingByRoomIdTest() throws Exception {

		List<RoomDetails> rooms = new ArrayList<RoomDetails>();
		RoomDetails roomDetails = new RoomDetails();
		roomDetails.setId(3);
		roomDetails.setSize("Double");
		roomDetails.setAvailability("true");
				
		rooms.add(roomDetails);
		
		Date checkInDate = Date.valueOf("2018-8-31");
		Date checkOutDate = Date.valueOf("2018-9-9");
		
		bookingDetailsDTO = new BookingDetailsDTO();
		bookingDetailsDTO.setCustomerId(1112);
		bookingDetailsDTO.setCheckIn(checkInDate);
		bookingDetailsDTO.setCheckOut(checkOutDate);
		bookingDetailsDTO.setBreakfastOption("true");
		bookingDetailsDTO.setRoomDetails(rooms);
		bookingDetailsDTO.setNoOfMembers(5);

		mvc.perform(MockMvcRequestBuilders.post("/create").content(toJson(bookingDetailsDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		mvc.perform(MockMvcRequestBuilders.get("/getBookingByRoomId/2").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").isNumber())
				.andExpect(jsonPath("$.customerId").isNumber()).andExpect(jsonPath("$.roomId").isNumber())
				.andExpect(jsonPath("$.checkIn").isString())
				.andExpect(jsonPath("$.checkOut").isString());

	}

	// retrieve booking by customer id
	@Test
	public void getBookingByCustomerIdTest() throws Exception {

		List<RoomDetails> rooms = new ArrayList<RoomDetails>();
		RoomDetails roomDetails = new RoomDetails();
		roomDetails.setId(2);
		roomDetails.setSize("Single");
		roomDetails.setAvailability("true");
		rooms.add(roomDetails);
				
		Date checkInDate = Date.valueOf("2018-8-31");
		Date checkOutDate = Date.valueOf("2018-9-9");
		
		bookingDetailsDTO = new BookingDetailsDTO();
		bookingDetailsDTO.setCustomerId(1113);
		bookingDetailsDTO.setCheckIn(checkInDate);
		bookingDetailsDTO.setCheckOut(checkOutDate);
		bookingDetailsDTO.setBreakfastOption("false");
		bookingDetailsDTO.setRoomDetails(rooms);

		mvc.perform(MockMvcRequestBuilders.post("/create").content(toJson(bookingDetailsDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		mvc.perform(MockMvcRequestBuilders.get("/getBookingByCustomerId/1113")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$.id").isNumber()).andExpect(jsonPath("$.customerId").isNumber())
				.andExpect(jsonPath("$.roomId").isNumber())
				.andExpect(jsonPath("$.checkIn").isString()).andExpect(jsonPath("$.checkOut").isString());

	}

	// update booking by booking id
	@Test
	public void updateBookingByBookingIdTest() throws Exception {

		List<RoomDetails> rooms = new ArrayList<RoomDetails>();
		RoomDetails roomDetails = new RoomDetails();
		roomDetails.setId(4);
		roomDetails.setSize("Double");
		roomDetails.setAvailability("true");
		rooms.add(roomDetails);
				
		Date checkInDate = Date.valueOf("2018-8-31");
		Date checkOutDate = Date.valueOf("2018-9-9");
		
		bookingDetailsDTO = new BookingDetailsDTO();
		bookingDetailsDTO.setCustomerId(1114);
		bookingDetailsDTO.setCheckIn(checkInDate);
		bookingDetailsDTO.setCheckOut(checkOutDate);
		bookingDetailsDTO.setBreakfastOption("false");
		bookingDetailsDTO.setRoomDetails(rooms);

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post("/create").content(toJson(bookingDetailsDTO))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.totalCost").isNumber())
				.andExpect(jsonPath("$.bookingId").isNumber()).andReturn();

		JSONObject json = new JSONObject(result.getResponse().getContentAsString());
		Integer id = (Integer) json.getInt("bookingId");

		bookingDetailsDTO.setCustomerId(1115);

		mvc.perform(MockMvcRequestBuilders.put("/updateExistingBooking/"+id).content(toJson(bookingDetailsDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
		mvc.perform(
                MockMvcRequestBuilders.get("/getBookingByRoomId/4").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.bookingId").value(json.get("bookingId")))
                .andExpect(jsonPath("$.roomId").value(4));


	}
	
	// update booking by booking id
	/*	@Test
	public void getBookingWithinADateRangeTest() throws Exception {

			Date checkInDate = Date.valueOf("2018-8-31");
			Date checkOutDate = Date.valueOf("2018-9-9");
			
		bookingDetails = new BookingDetails();
		bookingDetails.setCustomerId(1114);
		bookingDetails.setCheckIn(checkInDate);
		bookingDetails.setCheckOut(checkOutDate);

		mvc.perform(MockMvcRequestBuilders.post("/create").content(toJson(bookingDetails))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

		mvc.perform(
				MockMvcRequestBuilders.get("/getBookingWithinADateRange/checkIn=2018-8-31&checkOut=2018-9-9")
						.content(toJson(bookingDetails)).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}*/

	private byte[] toJson(Object r) throws Exception {
		ObjectMapper map = new ObjectMapper();
		return map.writeValueAsString(r).getBytes();
	}
} 