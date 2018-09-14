package com.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * Model class for the ROOM_DETAILS table.
 * @author isapjtg
 *
 */
@Entity
@Table (name = "RoomDetails")
public class RoomDetails {
	
	@Id
	@JoinColumn(name = "id", nullable=false)
	private Integer id;
	@Column(name = "size")
	private String size;
	@Column(name = "availability")
	private String availability;
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getAvailability() {
			return availability;
		}
		public void setAvailability(String availability) {
			this.availability = availability;
		}
		public String getSize() {
			return size;
		}
		public void setSize(String size) {
			this.size = size;
		}
}
