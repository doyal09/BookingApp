package com.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	@Column(name = "roomId", nullable = false)
	private Integer roomId;
	@Column(name = "size")
	private String size;

	/*
	 * Get ID of Room
	 * return Integer id
	 */
	public Integer getRoomId() {
		return roomId;
	}

	/*
	 * Sets ID of Room
	 * @param id
	 */
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
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
