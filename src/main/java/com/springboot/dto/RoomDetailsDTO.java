package com.springboot.dto;

/*
 * DTO Class for getting the Room Details
 */
public class RoomDetailsDTO {
	
	private Integer id;
	private String availability;
	private String size;
	
	/*
	 * Getting the ID of room
	 * returns Integer id
	 */
	public Integer getId() {
		return id;
	}
	
	/*
	 *Setting the Room ID 
	 *@param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/*
	 * Getting the room availability
	 * returns string
	 */
	public String getAvailability() {
		return availability;
	}
	
	/*
	 * Setting the room availability
	 * @param availability
	 */
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	/*
	 * Getting the size of the room
	 * returns string 
	 */
	public String getSize() {
		return size;
	}
	
	/*
	 * Setting the size of the room
	 * @param size
	 */
	public void setSize(String size) {
		this.size = size;
	}
}
