package com.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * Model class for the RoomDetails table.
 * @author isapjtg
 *
 */
@Entity
@Table (name = "RoomDetails")
public class RoomDetails {

	@Id
	@JoinColumn(name = "id", nullable = false)
	private Integer id;
	@Column(name = "size")
	private String size;
	@Column(name = "availability")
	private String availability;

	/*
	 * Get ID of Room
	 * return Integer id
	 */
	public Integer getId() {
		return id;
	}

	/*
	 * Sets ID of Room
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/*
	 * Get availability of Room
	 * return String availability
	 */
	public String getAvailability() {
		return availability;
	}

	/*
	 * Sets availability of room
	 * @param availability
	 */
	public void setAvailability(String availability) {
		this.availability = availability;
	}

	/*
	 * Gets the size of the room
	 * return String size
	 */
	public String getSize() {
		return size;
	}

	/*
	 *Set size of Room
	 *@param String size 
	 */
	public void setSize(String size) {
		this.size = size;
	}
}
